package service;

import dto.DTOToy;
import models.TypeToy;

import java.util.List;
import java.util.Map;

public interface ToyService {
    void addToy(DTOToy toy) throws Exception;
    Map<TypeToy, Integer> showByType() throws Exception;
    int totalCount() throws Exception;
    double totalValue() throws Exception;
    void decreaseTotal(String toyName, int quantity) throws Exception;
    void increaseTotal(String toyName, int quantity) throws Exception;
    Map.Entry<TypeToy, Integer> mxToy() throws Exception;
    Map.Entry<TypeToy,Integer> minToy() throws Exception;
    List<DTOToy> filterByPrice(double minPrice) throws Exception;
    List<DTOToy> sortByQuantity() throws Exception;
}
