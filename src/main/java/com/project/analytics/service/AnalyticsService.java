package com.project.analytics.service;

import org.springframework.stereotype.Service;

import com.project.analytics.entity.Metrics;
import com.project.analytics.repository.MetricsRepository;
import com.project.event.OrderCreatedEvent;

@Service
public class AnalyticsService {

    private final MetricsRepository metricsRepository;

    public AnalyticsService(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }


    public void processOrderEvent(OrderCreatedEvent event) {

        Metrics orderCount = new Metrics();

        orderCount.setMetricName("TOTAL_ORDERS");
        orderCount.setMetricValue(1);

        metricsRepository.save(orderCount);


        Metrics revenue = new Metrics();

        revenue.setMetricName("TOTAL_REVENUE");
        revenue.setMetricValue(event.getPrice());

        metricsRepository.save(revenue);
    }
}