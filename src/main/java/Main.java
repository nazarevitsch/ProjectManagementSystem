import com.bida.dbconection.domain.ProgramingLanguage;
import com.bida.dbconection.domain.SkillsLevel;
import com.bida.dbconection.service.DeveloperService;
import com.bida.dbconection.service.ProjectService;

public class Main {

    public static void main(String[] args) {
        DeveloperService developerService = new DeveloperService();
        ProjectService projectService = new ProjectService();

        System.out.println(developerService.findAllDevelopersByProgramingLanguage(ProgramingLanguage.Java));
        System.out.println(developerService.findAllDevelopersBySkillLevel(SkillsLevel.Senior));
        System.out.println(developerService.findAllDevelopersByProjectId(2));
        System.out.println(developerService.findDevelopersSalaryByIdProject(3));
        System.out.println(developerService.findAllDevelopers());

        System.out.println(projectService.findAllProjects());
    }
}
