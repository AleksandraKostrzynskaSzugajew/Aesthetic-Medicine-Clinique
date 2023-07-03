package pl.alekosszu.KME.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@ToString
public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @NotBlank
        private String name;


}
