package com.felipebueno.springshop;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipebueno.springshop.domain.Address;
import com.felipebueno.springshop.domain.Category;
import com.felipebueno.springshop.domain.City;
import com.felipebueno.springshop.domain.Client;
import com.felipebueno.springshop.domain.Demand;
import com.felipebueno.springshop.domain.ItemDemand;
import com.felipebueno.springshop.domain.Payment;
import com.felipebueno.springshop.domain.PaymentWithBill;
import com.felipebueno.springshop.domain.PaymentWithCard;
import com.felipebueno.springshop.domain.Product;
import com.felipebueno.springshop.domain.State;
import com.felipebueno.springshop.domain.enums.ClientType;
import com.felipebueno.springshop.domain.enums.StatePayment;
import com.felipebueno.springshop.repositories.AddressRepository;
import com.felipebueno.springshop.repositories.CategoryRepository;
import com.felipebueno.springshop.repositories.CityRepository;
import com.felipebueno.springshop.repositories.ClientRepository;
import com.felipebueno.springshop.repositories.DemandRepository;
import com.felipebueno.springshop.repositories.ItemDemandRepository;
import com.felipebueno.springshop.repositories.PaymentRepository;
import com.felipebueno.springshop.repositories.ProductRepository;
import com.felipebueno.springshop.repositories.StateRepository;

@SpringBootApplication
public class SpringShopApplication implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	DemandRepository demandRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ItemDemandRepository itemDemandRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1, cat2));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "Maria@gmail.com", "36378912377", ClientType.PESSOAFISICA);
		cli1.getTelephones().addAll(Arrays.asList("27363323", "93838398"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardins", "382208384", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Demand ped1 = new Demand(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Demand ped2 = new Demand(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pagto1 = new PaymentWithCard(null, StatePayment.QUITADO, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentWithBill(null, StatePayment.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getDemands().addAll(Arrays.asList(ped1, ped2));

		demandRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemDemand ip1 = new ItemDemand(ped1, p1, 0.0, 1, 2000.0);
		ItemDemand ip2 = new ItemDemand(ped1, p3, 0.0, 2, 80.0);
		ItemDemand ip3 = new ItemDemand(ped2, p2, 100.0, 1, 800.0);

		ped1.getItems().addAll(Arrays.asList(ip1, ip2));
		ped2.getItems().addAll(Arrays.asList(ip3));

		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));

		itemDemandRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
