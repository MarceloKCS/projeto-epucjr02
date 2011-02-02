package com.epucjr.engyos.teste;

import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class TesteAny {
	
	public static void main(String[] args){
		TesteAny.inserirThings();
	}
	
	public static void inserirThings(){
		Product product = new Product();
		product.setName("Calculador");
		ProductItem productItem = new ProductItem();
		Item item = new Item();
		item.setName("Pilha");
		productItem.setItem(item);
		
		product.getProductItems().add(productItem);
		
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		dataAccessObjectManager.persistirObjeto(product);
		
		
	}

}
