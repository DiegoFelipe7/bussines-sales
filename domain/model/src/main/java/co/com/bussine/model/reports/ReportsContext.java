package co.com.bussine.model.reports;
import co.com.bussine.model.reports.gateways.ReportsRepository;
import lombok.Getter;
import lombok.Setter;

@Setter
public class ReportsContext {
    private final ReportsRepository reportsRepository;


    public ReportsContext(ReportsRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

}
