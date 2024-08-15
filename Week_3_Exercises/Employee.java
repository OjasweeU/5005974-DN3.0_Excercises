import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

@Entity
@DynamicInsert
@DynamicUpdate
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Double salary;

    @Formula("salary * 0.1") // Example: 10% bonus
    private Double bonus;

    @Type(type = "org.hibernate.type.StringNVarcharType") // Custom type example
    private String customField;

    // Getters and Setters
}
