package co.com.bussine.jpa.category;


import co.com.bussine.jpa.category.mapper.CategoryMapper;
import co.com.bussine.jpa.helper.AdapterOperations;
import co.com.bussine.model.category.Category;
import co.com.bussine.model.category.gateways.CategoryRepository;
import co.com.bussine.model.common.BusinessException;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryJPARepositoryAdapter extends AdapterOperations<Category, CategoryDto, String, CategoryJPARepository>
        implements CategoryRepository {
    public CategoryJPARepositoryAdapter(CategoryJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Category.CategoryBuilder.class).build());

    }

    @Override
    public Mono<ResponseShopSavvyDTO<List<Category>>> getAllCategory(PaginationDTO paginationDTO) {
        return Flux.fromIterable(repository.findAllBySearchLike(paginationDTO.getSearch()))
                .filter(ele -> ele.getStatus().equals(Boolean.TRUE))
                .map(CategoryMapper::categoryDtoACategory)
                .skip(paginationDTO.getOffset())
                .take(paginationDTO.getLimit())
                .collectList()
                .zipWith(Mono.just(repository.count()))
                .map(ele -> new ResponseShopSavvyDTO<>(ele.getT1(), paginationDTO.pagination(ele.getT2())));

    }

    @Override
    public Mono<ResponseShopSavvy<Category>> getIdCategory(String id) {
        return repository.findById(id)
                .map(ele -> new ResponseShopSavvy<>(CategoryMapper.categoryDtoACategory(ele)))
                .map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTA_CATEGORIA));
    }

    @Override
    public Mono<ResponseShopSavvy<Category>> createCategory(Category category) {
        CategoryDto categoryDto = repository.save(CategoryMapper.categoryACategoryDto(category));
        return Mono.just(new ResponseShopSavvy<>(CategoryMapper.categoryDtoACategory(categoryDto)));

    }

    @Override
    public Mono<ResponseShopSavvy<Category>> updateCategory(String id, Category category) {
        return repository.findById(id)
                .map(ele -> {
                    category.setId(id);
                    CategoryDto categoryDto = repository.save(CategoryMapper.categoryACategoryDto(category));
                    return new ResponseShopSavvy<Category>(CategoryMapper.categoryDtoACategory(categoryDto));
                })
                .map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTA_CATEGORIA));
    }


    @Override
    public Mono<ResponseShopSavvy<Category>> updateStatus(String id) {
        return repository.findById(id)
                .map(category -> {
                    category.setStatus(!category.getStatus());
                    CategoryDto categoryDto = repository.save(category);
                    return new ResponseShopSavvy<>(CategoryMapper.categoryDtoACategory(categoryDto));
                })
                .map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTA_CATEGORIA));
    }

    @Override
    public Mono<byte[]> generateReport() {
        return Mono.fromCallable(() -> JasperExportManager.exportReportToPdf(this.getReport(repository.findAll()
                .stream()
                .map(CategoryMapper::categoryDtoACategory)
                .toList())));

    }



    private JasperPrint getReport(List<Category> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("petsData", new JRBeanCollectionDataSource(list));
        return JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:report.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());
    }

    @Override
    public Mono<byte[]> exportToXls() {
        /*ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(repository.findAll().stream().map(CategoryMapper::categoryDtoACategory).toList())));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return Mono.just(byteArray.toByteArray());*/
        return null;
    }



}
