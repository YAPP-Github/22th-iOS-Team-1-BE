package kr.co.yapp._22nd.coffice.domain.member;

import jakarta.persistence.*;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProvider;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.name.MemberName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: 권한, fcmToken
@Entity
@Getter
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @ElementCollection
    @CollectionTable(name = "member_auth_provider", joinColumns = @JoinColumn(name = "memberId"))
    private List<AuthProvider> authProviders = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Member from(MemberCreateVo memberCreateVo) {
        Member member = new Member();
        member.name = memberCreateVo.getName();
        member.status = MemberStatus.ACTIVE;
        member.authProviders.add(AuthProvider.from(memberCreateVo.getAuthProviderCreateVo()));
        return member;
    }

    public void addAuthProvider(AuthProviderCreateVo authProviderCreateVo) {
        this.authProviders.add(AuthProvider.from(authProviderCreateVo));
    }

    public Member updateName(MemberName memberName) {
        this.name = memberName.value();
        return this;
    }
}
