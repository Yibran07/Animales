/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class AnimalesModel {
    private String color;
    private String especie;
    private String tipo_animal;
    private String tipo_alimento;
    private double peso;
    private String habitad;
    private String altura;
    private int id;
    
    public AnimalesModel(){
        
    }
    
    public AnimalesModel(String color, String especie, String tipo_animal, String tipo_alimento, double peso, String habitad, String altura, int id){
        this.color = color;
        this.especie = especie;
        this.tipo_animal = tipo_animal;
        this.tipo_alimento = tipo_alimento;
        this.peso = peso;
        this.habitad = habitad;
        this.altura = altura;
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getTipo_animal() {
        return tipo_animal;
    }

    public void setTipo_animal(String tipo_animal) {
        this.tipo_animal = tipo_animal;
    }

    public String getTipo_alimento() {
        return tipo_alimento;
    }

    public void setTipo_alimento(String tipo_alimento) {
        this.tipo_alimento = tipo_alimento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getHabitad() {
        return habitad;
    }

    public void setHabitad(String habitad) {
        this.habitad = habitad;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Animal{" + "color=" + color + ", especie=" + especie + ", tipo_animal=" + tipo_animal + ", tipo_alimento=" + tipo_alimento + ", peso=" + peso + ", habitad=" + habitad + ", altura=" + altura + ", id=" + id +'}';
    } 
}
