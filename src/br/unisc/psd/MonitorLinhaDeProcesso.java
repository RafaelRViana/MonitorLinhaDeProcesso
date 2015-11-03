package br.unisc.psd;

import gnu.io.CommPortIdentifier;
import java.util.Enumeration;


/**
 * @author rafael.viana
 */
public class MonitorLinhaDeProcesso extends javax.swing.JFrame {

    private StringBuilder console = new StringBuilder();
    
    private int contadorSensorA = 0;
    private int contadorSensorB = 0;
    private int contadorSensorC = 0;
    
    private SerialPortReader reader;
    private SensorTableModel model;
    
    public void registerEvent(String event) {
        SensorEvent sensorEvent = new SensorEvent(event);
        model.add(sensorEvent);
        
        console.append("[DATA_RECEIVED] Recebido o comando: ").append(event).append("\n");
        
        if(sensorEvent.isSensorA()) {
            contadorSensorA++;
        } else if(sensorEvent.isSensorB()) {
            contadorSensorB++;
        } else if(sensorEvent.isSensorC()) {
            contadorSensorC++;
        } else if(sensorEvent.isSensorReset()) {
            reset();
            console.append("Sistema foi reiniciado...").append("\n");
        }
        
        
        atualizarTela();
    }
    
    public void reset() {
        contadorSensorA = 0;
        contadorSensorB = 0;
        contadorSensorC = 0;
        console = new StringBuilder();
        model.removeAll();
    }
    
    private void atualizarTela() {
        lblSensorA.setText(String.format("%03d", contadorSensorA));
        lblSensorB.setText(String.format("%03d", contadorSensorB));
        lblSensorC.setText(String.format("%03d", contadorSensorC));
        jTable1.scrollRectToVisible(jTable1.getCellRect(jTable1.getRowCount()-1, jTable1.getColumnCount(), true));
        //txtConsole.setText(console.toString());
    }
    
    public MonitorLinhaDeProcesso() {
        initComponents();
        
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId;
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals("COM1")) {
                    reader = new SerialPortReader(portId, this);
                }
            }
        }
        
        model = new SensorTableModel();
        jTable1.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblSensorA = new javax.swing.JLabel();
        lblSensorB = new javax.swing.JLabel();
        lblSensorC = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monitor de Linha de Processo");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                aoFecharJanela(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sensor", "Comando", "Data / Horário"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        lblSensorA.setFont(new java.awt.Font("Tahoma", 0, 48));
        lblSensorA.setForeground(new java.awt.Color(0, 153, 0));
        lblSensorA.setText("000");

        lblSensorB.setFont(new java.awt.Font("Tahoma", 0, 48));
        lblSensorB.setForeground(new java.awt.Color(0, 51, 204));
        lblSensorB.setText("000");

        lblSensorC.setFont(new java.awt.Font("Tahoma", 0, 48));
        lblSensorC.setForeground(new java.awt.Color(255, 51, 255));
        lblSensorC.setText("000");

        jLabel4.setText("Sensor A");

        jLabel5.setText("Sensor B");

        jLabel6.setText("Sensor C");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSensorA, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSensorB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblSensorC, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSensorA, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSensorB, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSensorC, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aoFecharJanela(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_aoFecharJanela
        
    }//GEN-LAST:event_aoFecharJanela

    //FIXME Fechar porta ao fechar aplicação
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MonitorLinhaDeProcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonitorLinhaDeProcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonitorLinhaDeProcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonitorLinhaDeProcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MonitorLinhaDeProcesso().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblSensorA;
    private javax.swing.JLabel lblSensorB;
    private javax.swing.JLabel lblSensorC;
    // End of variables declaration//GEN-END:variables
}
