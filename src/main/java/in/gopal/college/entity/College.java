package in.gopal.college.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "college")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class College {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long collegeId;

	    private String collegeName;
	    private String address;
	    private String email;
	    private String phone;

	    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
	    private List<Student> students;

	    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
	    private List<Library> libraries;

	    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
	    private List<Hostel> hostels;

}
