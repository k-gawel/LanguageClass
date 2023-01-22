package language.contentandrepository.repository.impl;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.user.AppUserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class MockedAppUserRepository implements AppUserRepository {
    
    static final List<AppUser> users = List.of(
            new Teacher(new DomainID<>(Teacher.class, "teacher_1"), "teacher 1"),
            new Student(new DomainID<>(Student.class, "student_1"), "student 1"),
            new Student(new DomainID<>(Student.class, "student_2"), "student 2")
    );
    
    @Override
    public List<AppUser> find(Predicate<AppUser> predicate) {
        return new ArrayList<>(users.stream().filter(predicate).toList());
    }

    @Override
    public Optional<AppUser> findById(DomainID<AppUser> id) {
        return find(u -> u.id().equals(id)).stream().findAny();
    }

    @Override
    public Optional<AppUser> findById(String id) {
        return find(u -> u.id().id().equals(id)).stream().findAny();
    }

    @Override
    public List<AppUser> getByIds(List<DomainID<AppUser>> domainIDS) {
        return find(u -> domainIDS.contains(u.id()));
    }

    @Override
    public DomainID<AppUser> generateId(String baseId) {
        return null;
    }

    @Override
    public boolean add(AppUser object) {
        return false;
    }

    @Override
    public void delete(AppUser object) {

    }

    @Override
    public boolean isPresent(AppUser object) {
        return false;
    }
    
}
