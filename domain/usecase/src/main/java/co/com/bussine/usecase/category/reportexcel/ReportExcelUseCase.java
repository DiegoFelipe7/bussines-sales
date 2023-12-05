package co.com.bussine.usecase.category.reportexcel;

import co.com.bussine.model.category.gateways.CategoryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class ReportExcelUseCase implements Supplier<Mono<byte[]>> {
    private final CategoryRepository categoryRepository;
    @Override
    public Mono<byte[]> get() {
        return null;
    }
}
