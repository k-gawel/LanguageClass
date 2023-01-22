package language.contentandrepository.repository.impl;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.user.TeacherRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
public class MockedTeacherRepository  implements TeacherRepository   {

    static final List<Teacher> users = MockedAppUserRepository.users.stream()
            .filter(u -> u instanceof Teacher)
            .map(u -> (Teacher) u)
            .toList();

    @Override
    public List<Teacher> find(Predicate<Teacher> predicate) {
        return new ArrayList<>(users.stream().filter(predicate).toList());
    }

    @Override
    public Optional<Teacher> findById(DomainID<Teacher> id) {
        return find(u -> u.id().equals(id)).stream().findAny();
    }

    @Override
    public Optional<Teacher> findById(String id) {
        return find(u -> u.id().id().equals(id)).stream().findAny();
    }

    @Override
    public List<Teacher> getByIds(List<DomainID<Teacher>> domainIDS) {
        return find(u -> domainIDS.contains(u.id()));
    }

    @Override
    public DomainID<Teacher> generateId(String baseId) {
        return null;
    }

    @Override
    public boolean add(Teacher object) {
        return false;
    }

    @Override
    public void delete(Teacher object) {

    }

    @Override
    public boolean isPresent(Teacher object) {
        return false;
    }
    
}
