package dto;

import models.TypeToy;

public record DTOToy(String name, TypeToy type, double price, int quantity) {
}
