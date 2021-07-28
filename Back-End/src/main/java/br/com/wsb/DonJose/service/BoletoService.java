package br.com.wsb.DonJose.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.model.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 5);//5 dias de até o vencimento
		pagto.setDataVencimento(cal.getTime());
	}
}
