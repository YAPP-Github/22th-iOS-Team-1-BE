package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderDeleteVo;

import java.util.List;

public interface MemberCommandService {
    Member join(AuthProviderCreateVo authProviderCreateVo);

    Member connect(Long memberId, AuthProviderCreateVo authProviderCreateVo);

    void disconnect(Long memberId, List<AuthProviderDeleteVo> authProviderDeleteVos);
}
