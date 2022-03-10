package model;


import java.text.NumberFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class GraficoALinea{
    private JFreeChart chart;
    public GraficoALinea(final String titolo,ArrayList<Integer> giornate, String labelX, ArrayList<Double> serie1, String labelY,ArrayList<Double> serie2) {
        XYSeries s1=generaSerie("S1",serie1,giornate);
        XYSeries s2=generaSerie("S2",serie2,giornate);
        final XYDataset dataset = createDataset(s1,s2);
        final JFreeChart chart = createChart(dataset,labelX,labelY);
    }

    public JFreeChart getChart() {
        return chart;
    }

    /**
     * Creazione del dataset da utilizzare per la generazione del grafi
     * Ogni grafico ha un suo dataset specifico
     * @return un dataset con 2 serie.
     */
    private XYDataset createDataset(XYSeries s1,XYSeries s2) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }
    private XYSeries generaSerie(String label,ArrayList<Double> serie,ArrayList<Integer> giornate) {
        XYSeries s = new XYSeries(label);
        for(int i = 0; i <=serie.size(); i++)
            s.add(giornate.get(i),serie.get(i));
        return s;
    }

    /**
     * Metodo deputato alla creazione del grafico.
     * @param dataset  il dataset creato dal metodo createDataset
     * @return il grafico.
     */
    private JFreeChart createChart(final XYDataset dataset, String labelX, String labelY) {
        final JFreeChart chart = ChartFactory.createXYLineChart("Grafico a Linea", //titolo
                labelX, //label asse delle X
                labelY, //label asse dell Y
                dataset, // sorgente dei dati
                PlotOrientation.VERTICAL, //orientamento del grafico
                true, // mostra la legenda
                true, //usa i tooltip
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer =  new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(renderer);
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        XYItemLabelGenerator generator =
                new StandardXYItemLabelGenerator(
                        StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT, format, format);
        return chart;
    }
}