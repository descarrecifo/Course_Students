package com.example.demo.configuracion;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.stream.Stream;

@Component
public class ApplicationCommandRunner implements CommandLineRunner {

    //protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // save students
        studentRepository.save(new Student("Gabriella", "Montero", "IT", "gmontero@gmail.com"));
        studentRepository.save(new Student("Román", "Beyloc", "Business", "rbeyloc@gmail.com"));
        studentRepository.save(new Student("Albert", "Martinez", "Business", "amartinez@gmail.com"));
        studentRepository.save(new Student("Albert", "Picanyol", "IT", "apicanyol@gmail.com"));
        studentRepository.save(new Student("Blanca", "Gutierrez", "Business", "bgutierrez@gmail.com"));
        studentRepository.save(new Student("Elsa", "Figueras", "IT", "efigueras@gmail.com"));
        studentRepository.save(new Student("Ferran", "Arzamendia", "IT", "farzamendia@gmail.com"));
        studentRepository.save(new Student("David", "Escarre", "Business", "descarre@gmail.com"));
        studentRepository.save(new Student("Iván", "Palomo", "IT", "ipalomo@gmail.com"));
        studentRepository.save(new Student("Julio", "Queiroz", "Business", "jpqueiroz@gmail.com"));
        studentRepository.save(new Student("Ian", "Iazovskaia", "IT", "iiazovskaia@gmail.com"));


        Stream.of("Programming Java", "Spring Boot basics", "Programming Java II", "Programming React").forEach(name -> {
            courseRepository.save(new Course(name));
        });

        // Create users with BCrypt encoded password (user/user, admin/admin)
        // admin 1234,user 1234,david 1234,albert 1234

        User user1 = new User("user", "$2a$12$6SrTuULfPcPv6TXPUzttEuNRh27QfdofDclz4Bky0t/IJQFmP5qR2", "USER");
        User user2 = new User("admin", "$2a$12$6SrTuULfPcPv6TXPUzttEuNRh27QfdofDclz4Bky0t/IJQFmP5qR2", "ADMIN");
        User user3 = new User("david", "$2a$12$6SrTuULfPcPv6TXPUzttEuNRh27QfdofDclz4Bky0t/IJQFmP5qR2", "ADMIN");
        User user4 = new User("albert", "$2a$12$6SrTuULfPcPv6TXPUzttEuNRh27QfdofDclz4Bky0t/IJQFmP5qR2", "USER");
        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));
    }
}







