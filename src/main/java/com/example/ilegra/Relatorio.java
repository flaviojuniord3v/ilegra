package com.example.ilegra;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class Relatorio {

	private long quantidadeClientes;
	private long quantidadeVendedores;
	private BigDecimal valorVendaMaisCara;
	private String idVendaMaisCara;
	private BigDecimal valorPiorVendedor;
	private String piorVendedor;
	private Map<String, BigDecimal> mapPiorVendedor = new TreeMap<>();

	public Relatorio() {
		this.valorVendaMaisCara = BigDecimal.ZERO;
		this.valorPiorVendedor = new BigDecimal("1000000000");
	}

	public long getQuantidadeClientes() {
		return quantidadeClientes;
	}

	public void setQuantidadeClientes(long quantidadeClientes) {
		this.quantidadeClientes = quantidadeClientes;
	}

	public long getQuantidadeVendedores() {
		return quantidadeVendedores;
	}

	public void setQuantidadeVendedores(long quantidadeVendedores) {
		this.quantidadeVendedores = quantidadeVendedores;
	}

	public BigDecimal getValorVendaMaisCara() {
		return valorVendaMaisCara;
	}

	public void setValorVendaMaisCara(BigDecimal valorVendaMaisCara) {
		this.valorVendaMaisCara = valorVendaMaisCara;
	}

	public String getIdVendaMaisCara() {
		return idVendaMaisCara;
	}

	public void setIdVendaMaisCara(String idVendaMaisCara) {
		this.idVendaMaisCara = idVendaMaisCara;
	}

	public BigDecimal getValorPiorVendedor() {
		return valorPiorVendedor;
	}

	public void setValorPiorVendedor(BigDecimal valorPiorVendedor) {
		this.valorPiorVendedor = valorPiorVendedor;
	}

	public String getPiorVendedor() {
		return piorVendedor;
	}

	public void setPiorVendedor(String piorVendedor) {
		this.piorVendedor = piorVendedor;
	}

	public Map<String, BigDecimal> getMapPiorVendedor() {
		return mapPiorVendedor;
	}

	public void setMapPiorVendedor(Map<String, BigDecimal> mapPiorVendedor) {
		this.mapPiorVendedor = mapPiorVendedor;
	}

}
