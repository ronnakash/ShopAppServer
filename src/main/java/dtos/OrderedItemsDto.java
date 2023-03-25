/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entities.Item;
import entities.OrderedItems;

/**
 * @author ronna
 */
public class OrderedItemsDto implements Dto {
	private final Integer quantity;
	private final Integer orderId;
	private final ItemDto item;

	public OrderedItemsDto(Integer orderId, Item item, Integer quantity) {
		this.orderId = orderId;
		this.item = new ItemDto(item);
		this.quantity = quantity;
	}

	public OrderedItemsDto(OrderedItems orderedItems) {
		this(orderedItems.getOrderedItemsPK().getOrderId(), orderedItems.getItem(), orderedItems.getQuantity());
	}

	protected OrderedItemsDto(Integer orderId, Integer itemId, Integer quantity) {
		this.orderId = orderId;
		this.item = new ItemDto(itemId);
		this.quantity = quantity;
	}

	public static OrderedItemsDto shallowCopy(OrderedItems orderedItems) {
		return new OrderedItemsDto(orderedItems.getOrderedItemsPK().getOrderId(),
				orderedItems.getOrderedItemsPK().getItemId(), orderedItems.getQuantity());
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public ItemDto getItem() {
		return item;
	}

	public OrderedItems toEntity() {
		return new OrderedItems(this);
	}

}
