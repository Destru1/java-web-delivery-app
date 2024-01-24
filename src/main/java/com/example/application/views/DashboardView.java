package com.example.application.views;

import com.example.application.service.CourierService;
import com.example.application.service.DeliveryService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.context.annotation.Role;

@Route(value = "dashboard",layout = MainLayout.class)
@PageTitle("Dashboard")
@RolesAllowed("admin")
@PermitAll
public class DashboardView extends VerticalLayout {
    private final DeliveryService deliveryService;
    private final CourierService courierService;

    public DashboardView(DeliveryService deliveryService, CourierService courierService) {

        this.deliveryService = deliveryService;
        this.courierService = courierService;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getDeliveryStats(),getCourierChart());
    }

    private Component getDeliveryStats() {
        Span stats= new Span(deliveryService.countDeliveries() + " deliveries");
        stats.addClassNames("text-xl","mt-m");

        return stats;
    }

    private Component getCourierChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();

        courierService.findAllCouriers().forEach(courier -> {
            dataSeries.add(new DataSeriesItem(courier.getFullName(),courier.getCouriersCount()));
        });

        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}
