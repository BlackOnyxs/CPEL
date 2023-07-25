/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Logica.Equipo;
import Logica.EstadoPrestamo;
import Logica.Operador;
import Logica.Prestamo;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
public class LoansFrame extends javax.swing.JInternalFrame {

    DefaultTableModel loansTableModel;
    
    ArrayList<Prestamo> loans;
    Prestamo currentLoan;
    
    ArrayList<Equipo> equipments;
    Equipo currentEquipment;
    
    ArrayList<Operador> operators;
    Operador requestOperator;
    Operador returnOperator;
    
    ArrayList<EstadoPrestamo> loansStates;
    EstadoPrestamo currentState;
    
    int currentPage = 0;

    public LoansFrame() {
        initComponents();
        loansTableModel = new DefaultTableModel();
        loans = new ArrayList<>();

        loadEquipmentsFromDB();
        loadOperatorsFromDB();
        loadStatesFromDB();
        loadLoansFromDB(currentPage, 0);
    }

    public void loadLoansFromDB(int skip, int sort) {
        currentLoan = new Prestamo();
        loans = currentLoan.list(skip, sort);
        displayLoans();
    }

    public void loadEquipmentsFromDB() {
        currentEquipment = new Equipo();
        equipments = new ArrayList<>();
        equipments = currentEquipment.list(0, 0);

        if (!equipments.isEmpty()) {
            equipments.forEach(equipment -> {
                equipmentSelect.addItem(equipment.getDescripcion());
            });

            equipmentSelect.setSelectedIndex(0);
            currentEquipment = equipments.get(0);

            equipmentSelect.addItemListener((ItemEvent e) -> {
                currentEquipment.setDescripcion((String) equipmentSelect.getSelectedItem());
            });

        }
    }

    public void loadOperatorsFromDB() {
        requestOperator = new Operador();
        operators = requestOperator.list(0, 0);
        
        if (!operators.isEmpty()) {
            operators.forEach(operator -> {
                operatorRequestSelect.addItem((operator.getPrimerNombre() + " " + operator.getPrimerApellido()));
                operatorReturnSelect.addItem((operator.getPrimerNombre() + " " + operator.getPrimerApellido() ));
            });
            
            operatorRequestSelect.setSelectedIndex(0);
            requestOperator = operators.get(0);
            
            operatorReturnSelect.setSelectedIndex(0);
            returnOperator = operators.get(0);
            
            operatorRequestSelect.addItemListener((ItemEvent e) -> {
                requestOperator.setPrimerNombre((String) operatorRequestSelect.getSelectedItem());
            });

            operatorReturnSelect.addItemListener((ItemEvent e) -> {
                returnOperator.setPrimerNombre((String) operatorReturnSelect.getSelectedItem());
            });                        
        }
    }

    public void loadStatesFromDB() {
        currentState = new EstadoPrestamo();
        loansStates = new ArrayList<>();
        loansStates = currentState.list();

        if (!loansStates.isEmpty()) {
            loansStates.forEach(state -> {
                returnStateSelect.addItem(state.getEstadoDevolucion());
            });

            returnStateSelect.setSelectedIndex(0);
            currentState = loansStates.get(0);

            returnStateSelect.addItemListener((ItemEvent e) -> {
                currentState.setEstadoDevolucion((String) returnStateSelect.getSelectedItem());
            });

        }
    }
    public void displayLoans() {
        loansTableModel.setRowCount(0);

        loansTableModel = (DefaultTableModel) loansTable.getModel();

        if (!loans.isEmpty()) {
            loans.forEach( loan -> {
                Object[] rowData = {  loan.getIdPrestamo(), loan.getModeloEquipo(), ( loan.getNombreUsuario()+ " " +  loan.getFechaSolicitud()),
                     loan.getNombreOperadorPrestamo(),  loan.getFechaDevolicion(),  loan.getNombreOperadorDevolucion(), 
                     loan.getEstadoDevolicion(), loan.getObservacion() };
                loansTableModel.addRow(rowData);
            });
            loansTable.setModel(loansTableModel);
        }
    }

    private static String CapitalizaFully(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Arrays.stream(str.split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public ArrayList<String> validateFiels() {
        ArrayList<String> formFields = new ArrayList();
        String userValue = CapitalizaFully(userField.getText().trim());
        String observationValue = CapitalizaFully(observationField.getText().trim());

        if (userValue == null || userValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo cédula es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            userField.requestFocus();
            return formFields;
        }
        if (observationValue == null || observationValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo nombre es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            observationField.requestFocus();
            return formFields;
        }
        
        formFields.add(userValue);
        formFields.add(observationValue);
        return formFields;
    }

    public void clearFields() {
        userField.setText("");
        observationField.setText("");
        loanRequest.setText(new Date().toString());
        loadReturned.setText(new Date().toString());
    }

    public void addUser() {
        var formFields = validateFiels();
        /**TODO: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * Validate date
        **/
        equipments.forEach(equipment -> {
            if (equipment.getDescripcion().equals(currentEquipment.getDescripcion())) {
                currentEquipment.setIdEquipo(equipment.getIdEquipo());
            }
        });
        operators.forEach(operator -> {
            if ((operator.getPrimerNombre()+ " " +operator.getPrimerApellido() )
                            .equals(requestOperator.getPrimerNombre()+ " " + requestOperator.getPrimerApellido())) {
                requestOperator.setCedula(operator.getCedula());
            }
        });
        
        operators.forEach(operator -> {
            if ((operator.getPrimerNombre()+ " " +operator.getPrimerApellido() )
                            .equals(returnOperator.getPrimerNombre()+ " " + returnOperator.getPrimerApellido())) {
                returnOperator.setCedula(operator.getCedula());
            }
        });
        
        loansStates.forEach(state -> {
            if (state.getEstadoDevolucion().equals(currentState.getEstadoDevolucion())) {
                currentState.setIdEstado(state.getIdEstado());
            }
        });

        if (formFields.isEmpty())  return;
        
        int index = loans.indexOf(currentLoan);
        
        currentLoan = new Prestamo(new Date().toString(), currentEquipment.getIdEquipo(),
                formFields.get(0), requestOperator.getCedula(), new Date().toString(),
                returnOperator.getCedula(), currentState.getIdEstado(), formFields.get(1));
        System.out.println(currentLoan);
        Prestamo newLoan = currentLoan.save();
        if (newLoan != null) {
            if (index < 0) {
                loans.add(newLoan);
            } else {
                loans.set(index, newLoan );
            }

            displayLoans();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el usuario", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        CipLabel1 = new javax.swing.JLabel();
        equipmentSelect = new javax.swing.JComboBox<>();
        CipLabel5 = new javax.swing.JLabel();
        CipLabel6 = new javax.swing.JLabel();
        operatorRequestSelect = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        loanRequest = new javax.swing.JFormattedTextField();
        CipLabel7 = new javax.swing.JLabel();
        loadReturned = new javax.swing.JFormattedTextField();
        CipLabel8 = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        CipLabel9 = new javax.swing.JLabel();
        operatorReturnSelect = new javax.swing.JComboBox<>();
        CipLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        observationField = new javax.swing.JTextArea();
        CipLabel11 = new javax.swing.JLabel();
        returnStateSelect = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        loansTable = new javax.swing.JTable();
        btnAsc = new javax.swing.JButton();
        btnDsc = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnLoadFromDB = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales del Préstamo"));

        CipLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel1.setText("Fecha Solicitud");

        CipLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel5.setText("Equipo");

        CipLabel6.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel6.setText("Operador Préstamo");

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

        CipLabel7.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel7.setText("Fecha Devolución");

        loadReturned.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadReturnedActionPerformed(evt);
            }
        });

        CipLabel8.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel8.setText("Cédula Solicitante");

        CipLabel9.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel9.setText("Operador Devolución");

        CipLabel10.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel10.setText("Observación");

        observationField.setColumns(20);
        observationField.setRows(5);
        jScrollPane2.setViewportView(observationField);

        CipLabel11.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel11.setText("Estado Devolución");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CipLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CipLabel8)
                            .addComponent(CipLabel5)
                            .addComponent(CipLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(userField, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CipLabel9)
                                    .addComponent(CipLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(returnStateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(operatorReturnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(loanRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(equipmentSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(CipLabel7)
                                        .addComponent(CipLabel6))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(loadReturned)
                                        .addComponent(operatorRequestSelect, 0, 154, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CipLabel1)
                    .addComponent(loanRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipLabel7)
                    .addComponent(loadReturned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(equipmentSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipLabel5)
                    .addComponent(operatorRequestSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CipLabel8)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operatorReturnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipLabel9))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(CipLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(returnStateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CipLabel11))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Préstamos"));

        loansTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Equipo", "Solicitante", "Fecha Solicitud", "Operador Préstamo", "Fecha Devolución", "Operador Devolución", "Estado Devolución", "Observación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        loansTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loansTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(loansTable);
        if (loansTable.getColumnModel().getColumnCount() > 0) {
            loansTable.getColumnModel().getColumn(7).setResizable(false);
        }

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
                .addGap(47, 47, 47)
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnExport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDsc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAsc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLoadFromDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 785, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnLoadFromDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadFromDBActionPerformed


    }//GEN-LAST:event_btnLoadFromDBActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        addUser();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void loansTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loansTableMouseClicked
        String id = loansTable.getValueAt(loansTable.getSelectedRow(), 0).toString();

        currentLoan = loans.stream()
                .filter(loan -> loan.getIdPrestamo() == Integer.parseInt(id) )
                .findAny()
                .orElse(null);

        userField.setText(currentLoan.getCedulaUsuario());
        loanRequest.setText(currentLoan.getFechaSolicitud());
        loadReturned.setText(currentLoan.getFechaDevolicion());
        equipmentSelect.setSelectedItem(currentLoan.getModeloEquipo());
        operatorRequestSelect.setSelectedItem(currentLoan.getNombreOperadorPrestamo());
        operatorReturnSelect.setSelectedItem(currentLoan.getNombreOperadorDevolucion());
        observationField.setText(currentLoan.getObservacion());

        btnDelete.setEnabled(true);

    }//GEN-LAST:event_loansTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        String result = currentLoan.delete();
        if (result.equals("executed.")) {
            loans.removeIf(loan -> {
                return ( loan.getIdPrestamo() == currentLoan.getIdPrestamo());
            });
            displayLoans();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnAscMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAscMouseClicked
        loadLoansFromDB(currentPage, 0);
    }//GEN-LAST:event_btnAscMouseClicked

    private void btnDscMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDscMouseClicked
        loadLoansFromDB(currentPage, 1);
    }//GEN-LAST:event_btnDscMouseClicked

    private void btnImportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImportMouseEntered

    private void btnImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseClicked
        try {
            loans = FileUtils.loadLoansFromFile(this);
            displayLoans();
        } catch (IOException ex) {
            Logger.getLogger(LoansFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImportMouseClicked

    private void btnExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseClicked
        try {
            FileUtils.saveLoansToFile(loans, this);
        } catch (IOException ex) {
            Logger.getLogger(LoansFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportMouseClicked

    private void btnLoadFromDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadFromDBMouseClicked
        loadLoansFromDB(currentPage, 0);
    }//GEN-LAST:event_btnLoadFromDBMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        String searchValue = JOptionPane.showInputDialog("Ingrese el valor de busqueda");
        ArrayList<Prestamo> loansFound = currentLoan.search(searchValue.trim());
        if (loansFound.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se escontraron prestamos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        loans = loansFound;
        displayLoans();
    }//GEN-LAST:event_jButton2MouseClicked

    private void loadReturnedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadReturnedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loadReturnedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CipLabel1;
    private javax.swing.JLabel CipLabel10;
    private javax.swing.JLabel CipLabel11;
    private javax.swing.JLabel CipLabel5;
    private javax.swing.JLabel CipLabel6;
    private javax.swing.JLabel CipLabel7;
    private javax.swing.JLabel CipLabel8;
    private javax.swing.JLabel CipLabel9;
    private javax.swing.JButton btnAsc;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDsc;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnLoadFromDB;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> equipmentSelect;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFormattedTextField loadReturned;
    private javax.swing.JFormattedTextField loanRequest;
    private javax.swing.JTable loansTable;
    private javax.swing.JTextArea observationField;
    private javax.swing.JComboBox<String> operatorRequestSelect;
    private javax.swing.JComboBox<String> operatorReturnSelect;
    private javax.swing.JComboBox<String> returnStateSelect;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables
}
