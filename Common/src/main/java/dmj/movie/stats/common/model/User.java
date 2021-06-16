package dmj.movie.stats.common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Access(AccessType.FIELD)
@Table(name = "\"User\"")
public class User {

    @Id
    @Column(name = "\"GUID\"", nullable = false, length = 36)
    private String guid;
    @Column(name = "\"UserName\"", nullable = false, length = 500)
    private String userName;
    @Column(name = "\"Password\"", nullable = false, length = 500)
    private String password;
    @Column(name = "\"Email\"", length = 500)
    private String email;
    @Column(name = "\"Name\"", length = 500)
    private String name;
    @Column(name = "\"LastName\"", length = 500)
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User that = (User) o;

        return guid.equals(that.guid);
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }
}
