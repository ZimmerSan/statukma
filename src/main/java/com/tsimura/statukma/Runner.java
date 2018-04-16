package com.tsimura.statukma;

import com.tsimura.statukma.persistence.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final DisciplineRepository disciplineRepository;

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
//        List<Discipline> all = disciplineRepository.findAll();
//        all.stream()
//                .filter(d -> d.getSpeciality() == null)
//                .forEach(d -> {
//                    Optional<Discipline> discipline = all.stream().filter(a -> Objects.equals(a.getName(), d.getName()) && a.getSpeciality() != null).findFirst();
//                    if (discipline.isPresent()) {
//                        Discipline got = discipline.get();
//                        d.getEnrollments().forEach(e -> e.setDiscipline(got));
//                    }
//                });
//        int i;
//        disciplineRepository.saveAll(all);
    }
}
