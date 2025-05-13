package top.resty.project.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.resty.project.dto.ProductDTO;
import top.resty.project.entity.Products;
import top.resty.project.mapper.ProductDTOMapper;
import top.resty.project.service.*;

import java.util.List;

@Service
public class ProductDTOServiceImpl extends ServiceImpl<ProductDTOMapper, ProductDTO> implements ProductDTOService {

    @Autowired
    private ProductsService productsService;
    @Autowired
    private UpholsteryService upholsteryService;
    @Autowired
    private CartonDetailsService cartonDetailsService;
    @Autowired
    private ProductionLogisticsService productionLogisticsService;
    @Autowired
    private ProductDimensionsService productDimensionsService;
    @Autowired
    private SeatInnerComponentsService seatInnerComponentsService;
    @Autowired
    private SeatOuterComponentsService seatOuterComponentsService;
    @Autowired
    private BackInnerComponentsService backInnerComponentsService;
    @Autowired
    private BackOuterComponentsService backOuterComponentsService;
    @Autowired
    private ArmsService armsService;
    @Autowired
    private FoamDetailsService foamDetailsService;
    @Autowired
    private CastorsService castorsService;
    @Autowired
    private BasesService basesService;
    @Autowired
    private GasLiftService gasLiftService;
    @Autowired
    private GasLiftCoverService gasLiftCoverService;
    @Autowired
    private MechanismService mechanismService;
    @Autowired
    private FittingsService fittingsService;
    @Autowired
    private ProductImagesService productImagesService;

    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProductDTO productDTO) {
        try {
            // 批量插入数据
            productsService.saveBatch(List.of(productDTO.getProducts()));
            upholsteryService.saveBatch(List.of(productDTO.getUpholstery()));
            cartonDetailsService.saveBatch(List.of(productDTO.getCartonDetails()));
            productionLogisticsService.saveBatch(List.of(productDTO.getProductionLogistics()));
            productDimensionsService.saveBatch(List.of(productDTO.getProductDimensions()));
            seatInnerComponentsService.saveBatch(List.of(productDTO.getSeatInnerComponents()));
            seatOuterComponentsService.saveBatch(List.of(productDTO.getSeatOuterComponents()));
            backInnerComponentsService.saveBatch(List.of(productDTO.getBackInnerComponents()));
            backOuterComponentsService.saveBatch(List.of(productDTO.getBackOuterComponents()));
            armsService.saveBatch(List.of(productDTO.getArms()));
            foamDetailsService.saveBatch(List.of(productDTO.getFoamDetails()));
            castorsService.saveBatch(List.of(productDTO.getCastors()));
            basesService.saveBatch(List.of(productDTO.getBases()));
            gasLiftService.saveBatch(List.of(productDTO.getGasLift()));
            gasLiftCoverService.saveBatch(List.of(productDTO.getGasLiftCover()));
            mechanismService.saveBatch(List.of(productDTO.getMechanism()));
            fittingsService.saveBatch(List.of(productDTO.getFittings()));
            productImagesService.saveBatch(List.of(productDTO.getProductImages()));
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 新增方法：根据productId获取ProductDTO
    public ProductDTO getProductDTOById(Long productId) {
        ProductDTO productDTO = new ProductDTO();
        Products byId = productsService.getById(productId);
        System.out.println("byId = " + byId);
        productDTO.setProducts(productsService.getOne(new QueryWrapper().eq("id", productId)));
        productDTO.setUpholstery(upholsteryService.getByProductId(productId));
        productDTO.setCartonDetails(cartonDetailsService.getByProductId(productId));
        productDTO.setProductionLogistics(productionLogisticsService.getByProductId(productId));
        productDTO.setProductDimensions(productDimensionsService.getByProductId(productId));
        productDTO.setSeatInnerComponents(seatInnerComponentsService.getByProductId(productId));
        productDTO.setSeatOuterComponents(seatOuterComponentsService.getByProductId(productId));
        productDTO.setBackInnerComponents(backInnerComponentsService.getByProductId(productId));
        productDTO.setBackOuterComponents(backOuterComponentsService.getByProductId(productId));
        productDTO.setArms(armsService.getByProductId(productId));
        productDTO.setFoamDetails(foamDetailsService.getByProductId(productId));
        productDTO.setCastors(castorsService.getByProductId(productId));
        productDTO.setBases(basesService.getByProductId(productId));
        productDTO.setGasLift(gasLiftService.getByProductId(productId));
        productDTO.setGasLiftCover(gasLiftCoverService.getByProductId(productId));
        productDTO.setMechanism(mechanismService.getByProductId(productId));
        productDTO.setFittings(fittingsService.getByProductId(productId));
        productDTO.setProductImages(productImagesService.getByProductId(productId));
        return productDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeProductDTOById(Long productId) {
        try {
            productsService.removeById(productId);
            upholsteryService.removeByProductId(productId);
            cartonDetailsService.removeByProductId(productId);
            productionLogisticsService.removeByProductId(productId);
            productDimensionsService.removeByProductId(productId);
            seatInnerComponentsService.removeByProductId(productId);
            seatOuterComponentsService.removeByProductId(productId);
            backInnerComponentsService.removeByProductId(productId);
            backOuterComponentsService.removeByProductId(productId);
            armsService.removeByProductId(productId);
            foamDetailsService.removeByProductId(productId);
            castorsService.removeByProductId(productId);
            basesService.removeByProductId(productId);
            gasLiftService.removeByProductId(productId);
            gasLiftCoverService.removeByProductId(productId);
            mechanismService.removeByProductId(productId);
            fittingsService.removeByProductId(productId);
            productImagesService.removeByProductId(productId);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }
}
