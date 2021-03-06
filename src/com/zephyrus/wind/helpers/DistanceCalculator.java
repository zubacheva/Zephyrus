package com.zephyrus.wind.helpers;


import java.math.BigDecimal;																		// REVIEW: unused import
import java.util.ArrayList;


																									// REVIEW: too big gap


import com.zephyrus.wind.dao.interfaces.IServiceTypeDAO;
import com.zephyrus.wind.model.ServiceType;
import com.zephyrus.wind.dao.factory.OracleDAOFactory;
import com.zephyrus.wind.dao.interfaces.IDAO;
import com.zephyrus.wind.dao.interfaces.IProductCatalogDAO;
import com.zephyrus.wind.dao.interfaces.IProviderLocationDAO;
import com.zephyrus.wind.model.ProductCatalog;
import com.zephyrus.wind.model.ProductCatalogService;
import com.zephyrus.wind.model.ProviderLocation;
import com.zephyrus.wind.model.ServiceLocation;
import com.zephyrus.wind.model.User;
/**
 * 																									// REVIEW: documentation expected
 * @author Alexandra Beskorovaynaya
 *
 */
public class DistanceCalculator {
	private static final double EARTH_RADIUS = 6371.;
																									// REVIEW: documentation expected
	public double calculateDistance(ProviderLocation providerLocation, ServiceLocation serviceLocation){


		// Calculate the distance between points
		String[] prCoords = providerLocation.getLocationCoord().split(",");
		double providerLongitude = Double.parseDouble(prCoords[0]);
		double providerLatitude = Double.parseDouble(prCoords[1]);
		String[] serCoords = serviceLocation.getServiceLocationCoord().split(",");
		double servLongitude = Double.parseDouble(serCoords[0]);
		double servLatitude = Double.parseDouble(serCoords[1]);
		final double dlng = deg2rad(providerLongitude - servLongitude);
		final double dlat = deg2rad(providerLatitude - servLatitude);
		final double a = java.lang.Math.sin(dlat / 2) * java.lang.Math.sin(dlat / 2) + java.lang.Math.cos(deg2rad(servLatitude))
				* java.lang.Math.cos(deg2rad(providerLatitude)) * java.lang.Math.sin(dlng / 2) * java.lang.Math.sin(dlng / 2);
		final double c = 2 * java.lang.Math.atan2(java.lang.Math.sqrt(a), java.lang.Math.sqrt(1 - a))*EARTH_RADIUS;
		return c;
	}

	private static double deg2rad(final double degree) {
		return degree * (Math.PI / 180);
	}

	public ArrayList<ProductCatalog> getNearestProvidersServices(ServiceLocation sl, OracleDAOFactory oracleFactory) throws Exception {	// REVIEW: resembles DAO method structure, method could be placed into DAO. too long line

		IDAO<ProductCatalog> productCatalog = oracleFactory.getProductCatalogDAO();
		ArrayList<ProductCatalog> productCatalogs = productCatalog.findAll();
		IDAO<ProviderLocation> providerLocation = oracleFactory.getProviderLocationDAO();
		ArrayList<ProviderLocation> providerLocs= providerLocation.findAll();


		ArrayList<ProductCatalog> result = null;

		double minimalDistance = calculateDistance(providerLocs.get(1), sl);

		for (ProviderLocation entry: providerLocs) {
			double distance = calculateDistance(entry, sl);
			if (distance < minimalDistance) {
				result= new ArrayList<>();
				for(ProductCatalog pc: productCatalogs) {
					if(pc.getProviderLoc().getId() == entry.getId()) {
						result.add(pc);
					}
				}
			}

		}
		if (result==null) {
			result =  new ArrayList<>();
			for(ProductCatalog pc: productCatalogs) {
				if(pc.getProviderLoc().getId() == providerLocs.get(1).getId()) {
					result.add(pc);
				}
			}
		}
		return result;
	}

}
