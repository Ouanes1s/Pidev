/*
 * Copyright 2020-2021 LaynezCode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.stock.entites;

import java.io.InputStream;

public class Produit {
    private Integer id;
    private String barcode;
    private String productName;
    private Double purchasePrice;
    private Integer etat;
    private Double quantite;
    private String descriptionProduct;
    private InputStream productImage;

    public Produit() {
    }

    public Produit(Integer id, String barcode, String productName, Double purchasePrice, Integer etat, Double quantite, String descriptionProduct) {
        this.id = id;
        this.barcode = barcode;
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.etat = etat;
        this.quantite = quantite;
        this.descriptionProduct = descriptionProduct;
    }
    
    public Produit(Integer id, String barcode, String productName, Double purchasePrice, Integer etat, Double quantite, String descriptionProduct, InputStream productImage) {
        this.id = id;
        this.barcode = barcode;
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.etat = etat;
        this.quantite = quantite;
        this.descriptionProduct = descriptionProduct;
        this.productImage = productImage;
    }

    public Produit(Integer id, String barcode, String productName, Double purchasePrice, String descriptionProduct) {
        this.id=id;
        this.barcode=barcode;
        this.productName=productName;
        this.purchasePrice=purchasePrice;
        this.descriptionProduct=descriptionProduct;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }



    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public InputStream getProductImage() {
        return productImage;
    }

    public void setProductImage(InputStream productImage) {
        this.productImage = productImage;
    }
}
