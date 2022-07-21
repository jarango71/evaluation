package cl.nisum.evaluation;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("cl.nisum.evaluation");

        noClasses()
            .that()
            .resideInAnyPackage("cl.nisum.evaluation.service..")
            .or()
            .resideInAnyPackage("cl.nisum.evaluation.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..cl.nisum.evaluation.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
