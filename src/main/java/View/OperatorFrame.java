/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Logica.Operador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.FileUtils;

/**
 *
 * @author BlackOnyxs
 */
public class OperatorFrame extends javax.swing.JInternalFrame {

    DefaultTableModel operatorTableModel;
    ArrayList<Operador> operators;
    Operador currentOperator;

    int currentPage = 0;
    public OperatorFrame() {
        initComponents();
        operatorTableModel = new DefaultTableModel();
        operators = new ArrayList<>();
        
        loadFromDB( currentPage, 0);
    }
    
    
    public void loadFromDB(int skip, int sort) {
        currentOperator = new Operador();
        operators = currentOperator.list( skip, sort );
        displayUsers();
    }
    
    
    public void displayUsers() {
        operatorTableModel.setRowCount(0);
        
        operatorTableModel = (DefaultTableModel) operatorsTable.getModel();
        
        if ( !operators.isEmpty() ) {
            operators.forEach(operator -> {
                Object[] rowData = { operator.getCedula(), (operator.getPrimerNombre() + " " + operator.getPrimerApellido() ) };
                operatorTableModel.addRow(rowData);
            });
            operatorsTable.setModel(operatorTableModel);
        }
    }
    
    private static String CapitalizaFully( String str ) {
       if ( str == null || str.isEmpty() ) return str;
       return Arrays.stream( str.split("\\s+"))
               .map( t -> t.substring(0,1).toUpperCase()+ t.substring(1).toLowerCase())
               .collect(Collectors.joining(" "));
   }
    
      public ArrayList<String> validateFiels() {
       ArrayList<String> formFields = new ArrayList();
       String cipValue = CapitalizaFully(cipField.getText().trim());
       String nameValue = CapitalizaFully(nameField.getText().trim());
       String lastnameValue = CapitalizaFully(lastnameField.getText().trim());
        
        if( cipValue == null || cipValue.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "El campo cédula es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            cipField.requestFocus();
            return formFields;
        }
        if( nameValue == null || nameValue.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "El campo nombre es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            nameField.requestFocus();
            return formFields;
        }
        if( lastnameValue == null || lastnameValue.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "El campo apellidos es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            lastnameField.requestFocus();
            return formFields;
        }
        
        formFields.add(cipValue );
        formFields.add( nameValue );
        formFields.add( lastnameValue );
        return formFields;
   }
      
   public void clearFields() {
       cipField.setText("");
       nameField.setText("");
       lastnameField.setText("");
   }
      
   public void addOperator() {
       var formFields = validateFiels();
       
       
       if ( formFields.isEmpty() ) return;
       int index = operators.indexOf(currentOperator);
       currentOperator = new Operador( formFields.get(0), formFields.get(1), formFields.get(2) );
       
       Operador newUser = currentOperator.save();
       if ( newUser != null ) {
           if ( index < 0 ) {
               operators.add(newUser);
           } else {
               operators.set(index, newUser);
           }
           
           displayUsers();
           clearFields();
       }else{
           JOptionPane.showMessageDialog(this, "No se pudo guardar el usuario", "Error", JOptionPane.WARNING_MESSAGE);
       }
   }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        CipLabel = new javax.swing.JLabel();
        cipField = new javax.swing.JTextField();
        CipLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        CipLabel2 = new javax.swing.JLabel();
        lastnameField = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        operatorsTable = new javax.swing.JTable();
        btnAsc = new javax.swing.JButton();
        btnDsc = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnLoadFromDB = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales del Usuario"));

        CipLabel.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel.setText("Cédula");

        CipLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel1.setText("Nombre");

        CipLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel2.setText("Apellido");

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        jButton1.setText("Salir");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CipLabel)
                .addGap(18, 18, 18)
                .addComponent(cipField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(251, 251, 251)
                                .addComponent(CipLabel2)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lastnameField))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CipLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(173, 173, 173))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CipLabel)
                    .addComponent(cipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CipLabel1)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipLabel2)
                    .addComponent(lastnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Usuarios"));

        operatorsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cédula", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        operatorsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                operatorsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(operatorsTable);

        btnAsc.setText("Ascendente");
        btnAsc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAscMouseClicked(evt);
            }
        });

        btnDsc.setText("Descendente");
        btnDsc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDscMouseClicked(evt);
            }
        });

        btnExport.setText("Exportar");
        btnExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportMouseClicked(evt);
            }
        });

        btnImport.setText("Importar");
        btnImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnImportMouseEntered(evt);
            }
        });

        btnLoadFromDB.setText("Cargar DB");
        btnLoadFromDB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoadFromDBMouseClicked(evt);
            }
        });
        btnLoadFromDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadFromDBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnAsc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDsc)
                .addGap(32, 32, 32)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoadFromDB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDsc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAsc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLoadFromDB, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        addOperator();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        String result = currentOperator.delete();
       if ( result.equals("executed.") ) {
           operators.removeIf(user -> {
               return (user.getCedula() == null ? currentOperator.getCedula() == null : user.getCedula().equals(currentOperator.getCedula()));
           });
           displayUsers();
           clearFields();
       }else{
           JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.WARNING_MESSAGE);
       }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        String searchValue = JOptionPane.showInputDialog("Ingrese el valor de busqueda");
        ArrayList<Operador> operatorsFound = currentOperator.search(searchValue.trim());
        if ( operatorsFound.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "No se escontraron usuarios.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        operators = operatorsFound;
        displayUsers();
    }//GEN-LAST:event_jButton2MouseClicked

    private void btnLoadFromDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadFromDBActionPerformed

    }//GEN-LAST:event_btnLoadFromDBActionPerformed

    private void btnLoadFromDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadFromDBMouseClicked
        loadFromDB(currentPage, 0);
    }//GEN-LAST:event_btnLoadFromDBMouseClicked

    private void btnImportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImportMouseEntered

    private void btnImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseClicked
        try {
            operators = FileUtils.loadOperatorsFromFile(this);
            displayUsers();
        } catch (IOException ex) {
            Logger.getLogger(OperatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImportMouseClicked

    private void btnExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseClicked
        try {
            FileUtils.saveOperatorsToFile(operators, this);
        } catch (IOException ex) {
            Logger.getLogger(OperatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportMouseClicked

    private void btnDscMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDscMouseClicked
        loadFromDB( currentPage, 1);
    }//GEN-LAST:event_btnDscMouseClicked

    private void btnAscMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAscMouseClicked
        loadFromDB( currentPage, 0);
    }//GEN-LAST:event_btnAscMouseClicked

    private void operatorsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_operatorsTableMouseClicked
        String cip = operatorsTable.getValueAt(operatorsTable.getSelectedRow(), 0).toString();

        currentOperator = operators.stream()
        .filter(contact -> contact.getCedula().equals(cip))
        .findAny()
        .orElse(null);

        cipField.setText(cip);
        nameField.setText(currentOperator.getPrimerNombre());
        lastnameField.setText(currentOperator.getPrimerApellido());

        btnDelete.setEnabled(true);

    }//GEN-LAST:event_operatorsTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CipLabel;
    private javax.swing.JLabel CipLabel1;
    private javax.swing.JLabel CipLabel2;
    private javax.swing.JButton btnAsc;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDsc;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnLoadFromDB;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField cipField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastnameField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTable operatorsTable;
    // End of variables declaration//GEN-END:variables
}
