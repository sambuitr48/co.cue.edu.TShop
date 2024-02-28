package service.impl;

import dto.DTOToy;
import mapping.ToyMapper;
import models.Toy;
import models.TypeToy;
import service.ToyService;
import utils.Constants;
import utils.FUtils;

import java.io.File;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ServiceImpl implements ToyService {
    private final List<Toy> toyStore;

    public ServiceImpl() {
        toyStore = FUtils.getToys(new File(Constants.TOYS));
    }

    @Override
    public Future<?> addToy(DTOToy toy) throws Exception {
        Toy newToy = ToyMapper.mapFrom(toy);
        toyStore.add(newToy);
        FUtils.savedToys(new File(Constants.TOYS), toyStore);
        AllToys();
        return null;
    }

    @Override
    public Map<TypeToy, Integer> showByType() throws Exception {
        Map<TypeToy, Integer> showByType = new TreeMap<>();
        for (Toy toy : toyStore){
            TypeToy type = toy.getType();
            showByType.put(type, showByType.getOrDefault(type,0)+1);
        }
        return showByType;
    }

    @Override
    public int totalCount() throws Exception {
        int count = 0;
        for(Toy toy : toyStore){
            count += toy.getQuantity();
        }
        return count;
    }

    @Override
    public double totalValue() throws Exception {
        return toyStore.stream()
                .mapToDouble(toy -> toy.getPrice() * toy.getQuantity())
                .sum();
    }

    @Override
    public void decreaseTotal(String toyName, int quantity) throws Exception {
        Toy toyToUpdate = findByName(toyName);
        if (toyToUpdate.getQuantity() < quantity)
            throw new Exception("Insufficient quantity");
        toyToUpdate.setQuantity(toyToUpdate.getQuantity() - quantity);
        FUtils.savedToys(new File(Constants.TOYS), toyStore);
    }

    @Override
    public void increaseTotal(String toyName, int quantity) throws Exception {
        Toy toyToUpdate = findByName(toyName);
        toyToUpdate.setQuantity(toyToUpdate.getQuantity() + quantity);
        FUtils.savedToys(new File(Constants.TOYS), toyStore);
    }

    @Override
    public Map.Entry<TypeToy, Integer> mxToy() throws Exception {
        TypeToy firstKey = ((TreeMap<TypeToy, Integer>) showByType()).firstKey();
        Integer firstValue = showByType().get(firstKey);
        return new AbstractMap.SimpleEntry<>(firstKey, firstValue);
    }

    @Override
    public Map.Entry<TypeToy, Integer> minToy() throws Exception {
        TypeToy lastKey = ((TreeMap<TypeToy, Integer>) showByType()).lastKey();
        Integer lastValue = showByType().get(lastKey);
        return new AbstractMap.SimpleEntry<>(lastKey, lastValue);
    }

    @Override
    public List<DTOToy> filterByPrice(double minPrice) throws Exception {
        return toyStore.stream()
                .filter(toy -> toy.getPrice() > minPrice)
                .map(ToyMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<DTOToy> sortByQuantity() throws Exception {
        return toyStore.stream()
                .sorted(Comparator.comparingInt(Toy::getQuantity))
                .map(ToyMapper::mapFrom)
                .collect(Collectors.toList());
    }
    public List<DTOToy> AllToys() throws Exception {
        return toyStore.stream()
                .map(ToyMapper::mapFrom)
                .collect(Collectors.toList());
    }
    public Toy findByName(String toyName) throws Exception {
        return toyStore.stream()
                .filter(toy -> toy.getName().equalsIgnoreCase(toyName))
                .findFirst()
                .orElseThrow(() -> new Exception("Not found: " + toyName));
    }

}
