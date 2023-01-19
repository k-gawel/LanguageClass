package languageuser.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@Getter @Setter
public class AppUserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "app_user_id_seq", allocationSize = 1)
    private Long id;

    private String username;

    private String password;

    private String role;

}
