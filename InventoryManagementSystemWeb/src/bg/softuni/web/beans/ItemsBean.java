package bg.softuni.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bg.softuni.dto.ItemDto;

@ManagedBean(name = "itemsBean")
@SessionScoped
public class ItemsBean {

	private List<ItemDto> items;

	@PostConstruct
	public void init() {
		items = new ArrayList<ItemDto>();
		items.add(new ItemDto("GSM M10E (R01A06)", "	QB GSM/GPRS-12, TCPIP, HTTP, FTP", "GSM module", "M10ER01A06W32", "Comet electronics",
				"Project Bla bla 1", 15.277, 2000));
		items.add(new ItemDto("GSM M10E (R01A06)", "	QB GSM/GPRS-12, TCPIP, HTTP, FTP", "GSM module", "M10ER01A06W32", "Comet electronics",
				"Project Bla bla 2", 15.277, 1500));
		items.add(new ItemDto("GSM M10E (R01A06)", "	QB GSM/GPRS-12, TCPIP, HTTP, FTP", "GSM module", "M10ER01A06W32", "Comet electronics",
				"Project Bla bla 3", 15.277, 2500));
		items.add(new ItemDto("GSM M10E (R01A06)", "	QB GSM/GPRS-12, TCPIP, HTTP, FTP", "GSM module", "M10ER01A06W32", "Comet electronics",
				"Project Bla bla 4", 15.277, 2000));
		items.add(new ItemDto("GSM M10E (R01A06)", "	QB GSM/GPRS-12, TCPIP, HTTP, FTP", "GSM module", "M10ER01A06W32", "Comet electronics",
				"Project Bla bla 5", 15.277, 1380));
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public void setUsers(List<ItemDto> items) {
		this.items = items;
	}
}
