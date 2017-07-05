package by.epam.newsmanagement.service.factory;


public class ServiceFactory {
	
	private ServiceFactory() {
	}

	private static ServiceFactory serviceFactory;

	//private NewsService newsService = new NewsService();

	public static ServiceFactory getDaoFactory() {
		if (serviceFactory == null) {
			serviceFactory = new ServiceFactory();
		} 
		return serviceFactory;
	}
	
}
