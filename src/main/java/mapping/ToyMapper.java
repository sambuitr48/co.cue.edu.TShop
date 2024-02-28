package mapping;

import dto.DTOToy;
import models.Toy;

public class ToyMapper {
    public static Toy mapFrom(DTOToy dto) {
        return new Toy(dto.name(), dto.type(), dto.price(), dto.quantity());
    }
    public static DTOToy mapFrom(Toy model){
        return new DTOToy(model.getName(), model.getType(), model.getPrice(), model.getQuantity());
    }
}
