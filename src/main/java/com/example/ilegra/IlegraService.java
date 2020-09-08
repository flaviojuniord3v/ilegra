package com.example.ilegra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IlegraService {

	private static final Logger log = LoggerFactory.getLogger(FileWatcherConfig.class);
	private static final String SEPARATOR = "ç";

	@Value("${out-folder}")
	public String folderPath;

	private static Relatorio relatorio = new Relatorio();

	public void processInputFile(File file) {
		try (InputStream inputFS = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
				FileWriter fileWriter = new FileWriter(folderPath);) {
			while (br.readLine() != null) {
				String line = br.readLine();
				List<String> items = Arrays.asList(line.split(SEPARATOR));

				if (line.startsWith("001")) {
					long atual = relatorio.getQuantidadeVendedores();
					relatorio.setQuantidadeVendedores(++atual);
				} else if (line.startsWith("002")) {
					long atual = relatorio.getQuantidadeClientes();
					relatorio.setQuantidadeClientes(++atual);
				} else if (line.startsWith("003")) {
					List<String> venda = Arrays.asList(items.get(2).replaceAll("[^\\d.,-]", "").split(","));
					for (String item : venda) {
						List<String> valores = Arrays.asList(item.split("-"));
						BigDecimal valor = new BigDecimal(valores.get(1)).multiply(new BigDecimal(valores.get(2)));
						if (valor.compareTo(relatorio.getValorVendaMaisCara()) > 0) {
							relatorio.setValorVendaMaisCara(valor);
							relatorio.setIdVendaMaisCara(items.get(1));
						}

						if (!relatorio.getMapPiorVendedor().containsKey(items.get(3))) {
							relatorio.getMapPiorVendedor().put(items.get(3), valor);

							if (valor.compareTo(relatorio.getValorPiorVendedor()) < 0) {
								relatorio.setValorPiorVendedor(valor);
								relatorio.setPiorVendedor(items.get(3));
							}
						} else {
							BigDecimal valorSomado = relatorio.getMapPiorVendedor().get(items.get(3)).add(valor);
							relatorio.getMapPiorVendedor().put(items.get(3), valorSomado);
							if (valorSomado.compareTo(relatorio.getValorPiorVendedor()) < 0) {
								relatorio.setValorPiorVendedor(valorSomado);
								relatorio.setPiorVendedor(items.get(3));
							}
						}

					}

				}

			}

			fileWriter.write("Quantidade de clientes no arquivo de entrada: " + relatorio.getQuantidadeVendedores());
			fileWriter.write("Quantidade de vendedores no arquivo de entrada: " + relatorio.getQuantidadeClientes());
			fileWriter.write("ID da venda mais cara: " + relatorio.getIdVendaMaisCara());
			fileWriter.write("O pior vendedor: " + relatorio.getPiorVendedor());
		} catch (IOException e) {
			log.error("Exception", e);
		}
	}
}