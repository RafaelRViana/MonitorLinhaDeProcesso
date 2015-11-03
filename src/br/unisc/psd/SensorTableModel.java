package br.unisc.psd;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * @author rafael.viana
 */
public class SensorTableModel extends AbstractTableModel 
{

    private static final long serialVersionUID = 7238716733286975432L;
	
    private List<SensorEvent> linhas = new ArrayList<>();  
    private String [] colunas;
    
    public SensorTableModel()
    {
    	colunas = new String[]{"Sensor", "Comando", "Data/Horario"};
    }
    
    @Override
    public String getColumnName(int column) {
           return colunas[column];
    }
    
    public void add(SensorEvent sensor)
    {
    	linhas.add(sensor);
    	int linha = getLinhas().size()-1;  
        fireTableRowsInserted(linha,linha); 
    }
    
	public List<SensorEvent> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<SensorEvent> linhas) {
		this.linhas = linhas;
	}

	public String[] getColunas() {
		return colunas;
	}

	@Override
	public int getColumnCount() {
		return getColunas().length;
	}

	@Override
	public int getRowCount() {
		return getLinhas().size();
	}

	@Override
	public Object getValueAt(int indiceLinha, int indiceColuna) 
	{
            if(indiceLinha <= linhas.size()){
            SensorEvent sensor = (SensorEvent) linhas.get(indiceLinha);
		
	      switch (indiceColuna) 
	      {  
		      case 0:   
		         return sensor.getSensorName();  
		      case 1:   
		         return sensor.getSensor();  
                      case 2:
                         return sensor.getDate();
		      default:   
		         return null;
	      }
            } else {
                return null;
            }
	}

	public void removeAll() 
	{
		for(int i = 0; i < linhas.size(); i++)
		{
			fireTableRowsDeleted(i, i);
		}
		
		linhas = new ArrayList<>();
	}
	
	public void remove(int indice)
	{
		linhas.remove(indice);
		fireTableRowsDeleted(indice, indice);
	}

}