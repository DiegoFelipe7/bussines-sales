package co.com.bussine.model.reports.gateways;
import reactor.core.publisher.Mono;

public interface ReportsRepository {
   Mono<byte[]> generateReport();
}
