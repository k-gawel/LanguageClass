package language.contentandrepository.repository.impl;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.user.AppUserRepository;
import language.contentandrepository.repository.user.StudentRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Repository("appUserRepository")
@RequiredArgsConstructor
public class BaseAppUserRepository implements AppUserRepository {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<AppUser> find(Predicate<AppUser> predicate) {
        var teachers = teacherRepository.find(predicate::test);
        var students = studentRepository.find(predicate::test);
        var result = new ArrayList<AppUser>();
        result.addAll(teachers);
        result.addAll(students);
        return result;
    }

    @Override
    public Class<AppUser> provides() {
        return AppUser.class;
    }

    @Override
    public Optional<AppUser> findById(DomainID<AppUser> id) {
        return findById(id.id());
    }

    @Override
    public Optional<AppUser> findById(String id) {
        var result = teacherRepository.findById(id).map(t -> (AppUser) t).orElse(studentRepository.findById(id).orElse(null));
        return Optional.ofNullable(result);
    }

    @Override
    public List<AppUser> getByIds(List<DomainID<AppUser>> domainIDS) {
        return domainIDS.stream().map(this::findById).filter(Optional::isPresent).map(Optional::get).toList();
    }

    @Override
    public DomainID<AppUser> generateId(String baseId) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public boolean add(AppUser object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(AppUser object) {

    }

    @Override
    public boolean isPresent(AppUser object) {
        return object instanceof Teacher t ? teacherRepository.isPresent(t) : studentRepository.isPresent((Student) object);
    }
}
