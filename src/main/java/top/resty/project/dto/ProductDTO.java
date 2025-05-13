package top.resty.project.dto;

import lombok.Data;
import top.resty.project.entity.*;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年02月18 - 16:12
 */
@Data
public class ProductDTO {
    private Products products;
    private Upholstery upholstery;
    private CartonDetails cartonDetails;
    private ProductionLogistics productionLogistics;
    private ProductDimensions productDimensions;
    private SeatInnerComponents seatInnerComponents;
    private SeatOuterComponents seatOuterComponents;
    private BackInnerComponents backInnerComponents;
    private BackOuterComponents backOuterComponents;
    private Arms arms;
    private FoamDetails foamDetails;
    private Castors castors;
    private Bases bases;
    private GasLift gasLift;
    private GasLiftCover gasLiftCover;
    private Mechanism mechanism;
    private Fittings fittings;
    private ProductImages productImages;
}
