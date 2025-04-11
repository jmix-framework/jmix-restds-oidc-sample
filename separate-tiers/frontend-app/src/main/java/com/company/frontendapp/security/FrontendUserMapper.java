package com.company.frontendapp.security;

import com.company.frontendapp.entity.User;
import io.jmix.core.Metadata;
import io.jmix.oidc.claimsmapper.ClaimsRolesMapper;
import io.jmix.oidc.usermapper.BaseOidcUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class FrontendUserMapper extends BaseOidcUserMapper<User> {

    private final Metadata metadata;
    private final ClaimsRolesMapper claimsRolesMapper;

    public FrontendUserMapper(Metadata metadata, ClaimsRolesMapper claimsRolesMapper) {
        this.metadata = metadata;
        this.claimsRolesMapper = claimsRolesMapper;
    }

    @Override
    protected String getOidcUserUsername(OidcUser oidcUser) {
        return oidcUser.getPreferredUsername();
    }

    @Override
    protected User initJmixUser(OidcUser oidcUser) {
        return metadata.create(User.class);
    }

    @Override
    protected void populateUserAttributes(OidcUser oidcUser, User jmixUser) {
        jmixUser.setUsername(getOidcUserUsername(oidcUser));
        jmixUser.setFirstName(oidcUser.getGivenName());
        jmixUser.setLastName(oidcUser.getFamilyName());
        jmixUser.setEmail(oidcUser.getEmail());
    }

    @Override
    protected void populateUserAuthorities(OidcUser oidcUser, User jmixUser) {
        Collection<? extends GrantedAuthority> authorities = claimsRolesMapper.toGrantedAuthorities(oidcUser.getClaims());
        jmixUser.setAuthorities(authorities);
    }
}
