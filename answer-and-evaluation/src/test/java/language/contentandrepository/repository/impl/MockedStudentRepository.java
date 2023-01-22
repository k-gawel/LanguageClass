package language.contentandrepository.repository.impl;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.repository.user.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
public class MockedStudentRepository implements StudentRepository {

    static final List<Student> users = MockedAppUserRepository.users.stream()
            .filter(u -> u instanceof Student)
            .map(u -> (Student) u)
            .toList();

    @Override
    public List<Student> find(Predicate<Student> predicate) {
        return new ArrayList<>(users.stream().filter(predicate).toList());
    }

    @Override
    public Optional<Student> findById(DomainID<Student> id) {
        return find(u -> u.id().equals(id)).stream().findAny();
    }

    @Override
    public Optional<Student> findById(String id) {
        return find(u -> u.id().id().equals(id)).stream().findAny();
    }

    @Override
    public List<Student> getByIds(List<DomainID<Student>> domainIDS) {
        return find(u -> domainIDS.contains(u.id()));
    }

    @Override
    public DomainID<Student> generateId(String baseId) {
        return null;
    }

    @Override
    public boolean add(Student object) {
        return false;
    }

    @Override
    public void delete(Student object) {

    }

    @Override
    public boolean isPresent(Student object) {
        return false;
    }

}
