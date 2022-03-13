package rest_api04;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static rest_api04.ApplicationUserPermissions.*;
import com.google.common.collect.Sets;

public enum ApplicationUserRoles {

	STUDENT(Sets.newHashSet(STUDENT_READ)), ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE));
	
	private final Set<ApplicationUserPermissions> permission;

	private ApplicationUserRoles(Set<ApplicationUserPermissions> permission) {
		this.permission = permission;
	}

	public Set<ApplicationUserPermissions> getPermission() {
		return permission;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		
		Set<SimpleGrantedAuthority> permissions = getPermission().
															stream().
															map(permission -> new SimpleGrantedAuthority(permission.getPermission())).
															collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return permissions;
	}
	
}
