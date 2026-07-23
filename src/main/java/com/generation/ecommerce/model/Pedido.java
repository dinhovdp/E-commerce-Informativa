package com.generation.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

	@Id // PRIMARY KEY
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id AUTO_INCREMENT
    private Long id;

	@NotBlank(message = "O atributo produto é obrigatório!")
	@Size(min = 2, max = 100, message = "O atributo produto deve ter no mínimo 2 e no máximo 100 caracteres")
	@Column(length = 100)
    private String produto;

	@NotBlank(message = "O atributo descrição é obrigatório!")
	@Size(min = 2, max = 1000, message = "O atributo descrição deve ter no mínimo 2 e no máximo 1000 caracteres")
	@Column(length = 1000)
    private String descricao;

    @NotNull(message = "O valor é obrigatório!")
	@DecimalMin(value = "0.01", message = "O valor deve ser maior que zero!")
	@Digits(integer = 10, fraction = 2, message = "O valor deve ter no máximo 10 dígitos inteiros e 2 decimais")
	@Column(nullable = false, precision = 12, scale = 2) //total 12 digitos, sendo 2 decimais.
    private BigDecimal valor;

    
    //registra apenas a criacao do pedido e nao atualiza toda vez que altera
    @CreationTimestamp
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("pedidos")
    private Cliente cliente;

    public Pedido() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

  
}