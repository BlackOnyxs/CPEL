/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Logica.CategoriaEquipo;
import Logica.Equipo;
import Logica.EstadoEquipo;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.FileUtils;

/**
 *
 * @author BlackOnyxs
 */
public class EquipmentFrame extends javax.swing.JInternalFrame {

    DefaultTableModel equipmentsTableModel;

    ArrayList<Equipo> equipments;
    Equipo currentEquipment;

    ArrayList<CategoriaEquipo> equipmentTypes;
    CategoriaEquipo curretEquipmentCategories;

    ArrayList<EstadoEquipo> equipmentStates;
    EstadoEquipo currentEquipmentState;

    int currentPage = 0;
    Date currentDate;

    public EquipmentFrame() {
        initComponents();
        equipmentsTableModel = new DefaultTableModel();
        equipments = new ArrayList<>();

        loadCategoriesFromDB();
        loadCarrerFromDB();
        loadFromDB(currentPage, 0);
        System.out.println(currentEquipment);
        if (currentEquipment != null && currentEquipment.getFechaCompra() != null) {
            currentDate = new Date(currentEquipment.getFechaCompra());
        } else {
            currentDate = new Date();
        }
        setupDateField(currentDate);

    }

    public void setupDateField(Date date) {
        System.out.println(date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(date);
        System.out.println(dateString);
//        dateField = new JFormattedTextField(formatter);
        dateField.setColumns(10);
        dateField.setText(dateString);
    }

    public void loadFromDB(int skip, int sort) {
        currentEquipment = new Equipo();
        equipments = currentEquipment.list(skip, sort);
        displayEquipments();
    }

    public void loadCategoriesFromDB() {
        curretEquipmentCategories = new CategoriaEquipo();
        equipmentTypes = new ArrayList<>();
        equipmentTypes = curretEquipmentCategories.list();

        if (!equipmentTypes.isEmpty()) {
            equipmentTypes.forEach(type -> {
                cateorySelect.addItem(type.getNombreCategoria());
            });

//            stateSelect.setSelectedIndex(0);
            curretEquipmentCategories = equipmentTypes.get(0);

            stateSelect.addItemListener((ItemEvent e) -> {
                curretEquipmentCategories.setNombreCategoria((String) stateSelect.getSelectedItem());
            });

        }
    }

    public void loadCarrerFromDB() {
        currentEquipmentState = new EstadoEquipo();
        equipmentStates = currentEquipmentState.list();

        if (!equipmentStates.isEmpty()) {
            equipmentStates.forEach(state -> {
                stateSelect.addItem(state.getDescripcion());
            });
            cateorySelect.setSelectedIndex(0);
            currentEquipmentState = equipmentStates.get(0);
            cateorySelect.addItemListener((ItemEvent e) -> {
                currentEquipmentState.setDescripcion((String) cateorySelect.getSelectedItem());
            });

        }
    }

    public void displayEquipments() {
        equipmentsTableModel.setRowCount(0);

        equipmentsTableModel = (DefaultTableModel) usersTable.getModel();

        if (!equipments.isEmpty()) {
            equipments.forEach(equipment -> {
                Object[] rowData = {equipment.getIdEquipo(),
                    equipment.getDescripcion(), equipment.getModelo(),
                    equipment.getNombreCategoria(), equipment.getPlacaInventario(),
                    equipment.getFechaCompra(), equipment.getEstadoDescripcion(),};
                equipmentsTableModel.addRow(rowData);
            });
            usersTable.setModel(equipmentsTableModel);
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
        String boardValue = CapitalizaFully(boardField.getText().trim());
        String modelValue = CapitalizaFully(modelField.getText().trim());
        String descriptionValue = CapitalizaFully(descriptionField.getText().trim());

        if (boardValue == null || boardValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo cédula es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            boardField.requestFocus();
            return formFields;
        }
        if (modelValue == null || modelValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo nombre es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            modelField.requestFocus();
            return formFields;
        }
        if (descriptionValue == null || descriptionValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo apellidos es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            boardField.requestFocus();
            return formFields;
        }

        formFields.add(boardValue);
        formFields.add(modelValue);
        formFields.add(descriptionValue);
        return formFields;
    }

    public void clearFields() {
        modelField.setText("");
        boardField.setText("");
        descriptionField.setText("");
    }

    public void addEquipment() {
        var formFields = validateFiels();

        equipmentTypes.forEach(type -> {
            if (type.getNombreCategoria().equals(curretEquipmentCategories.getNombreCategoria())) {
                curretEquipmentCategories.setIdCategoria(type.getIdCategoria());
            }
        });

        equipmentStates.forEach(state -> {
            if (state.getDescripcion().trim().equals(currentEquipmentState.getDescripcion().trim())) {
                currentEquipmentState.setIdEstado(state.getIdEstado());
            }
        });

        if (formFields.isEmpty()) {
            return;
        }
        int index = equipments.indexOf(currentEquipment);
        currentEquipment = new Equipo(curretEquipmentCategories.getIdCategoria(),
                formFields.get(2), currentEquipmentState.getIdEstado(), formFields.get(1), formFields.get(0),
                currentEquipment.getFechaCompra(), "");

        Equipo newEquipment = currentEquipment.save();
        if (newEquipment != null) {
            if (index < 0) {
                equipments.add(newEquipment);
            } else {
                equipments.set(index, newEquipment);
            }

            displayEquipments();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el usuario", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        CipLabel = new javax.swing.JLabel();
        modelField = new javax.swing.JTextField();
        CipLabel1 = new javax.swing.JLabel();
        CipLabel2 = new javax.swing.JLabel();
        boardField = new javax.swing.JTextField();
        CipLabel3 = new javax.swing.JLabel();
        CipLabel4 = new javax.swing.JLabel();
        stateSelect = new javax.swing.JComboBox<>();
        CipLabel6 = new javax.swing.JLabel();
        cateorySelect = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        dateField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        btnAsc = new javax.swing.JButton();
        btnDsc = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnLoadFromDB = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales del Usuario"));

        CipLabel.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel.setText("Modelo");

        CipLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel1.setText("Descripción");

        CipLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel2.setText("Placa Inventario");

        CipLabel3.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel3.setText("Fecha de Compra");

        CipLabel4.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel4.setText("Estado");

        CipLabel6.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel6.setText("Categoría");

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

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        jScrollPane2.setViewportView(descriptionField);

        dateField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        dateField.setActionCommand("<Not Set>");

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel1.setText("Imagen");

        jButton3.setText("Seleccione una imagen");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CipLabel2)
                            .addComponent(CipLabel)
                            .addComponent(CipLabel3)
                            .addComponent(CipLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateField)
                            .addComponent(boardField)
                            .addComponent(modelField)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CipLabel4)
                            .addComponent(CipLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cateorySelect, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(72, 72, 72)
                        .addComponent(jButton3)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CipLabel2)
                            .addComponent(boardField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CipLabel)
                            .addComponent(modelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CipLabel4)
                            .addComponent(stateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cateorySelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CipLabel6))))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(CipLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3))
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Usuarios"));

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N.o", "Descripción", "Modelo", "Categoría", "Placa de Inventario", "Fecha de Compra", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usersTable);
        if (usersTable.getColumnModel().getColumnCount() > 0) {
            usersTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            usersTable.getColumnModel().getColumn(2).setPreferredWidth(20);
            usersTable.getColumnModel().getColumn(3).setPreferredWidth(25);
            usersTable.getColumnModel().getColumn(4).setPreferredWidth(25);
            usersTable.getColumnModel().getColumn(5).setPreferredWidth(15);
            usersTable.getColumnModel().getColumn(6).setPreferredWidth(15);
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
                .addComponent(btnLoadFromDB))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnLoadFromDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadFromDBActionPerformed


    }//GEN-LAST:event_btnLoadFromDBActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        addEquipment();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
        String id = usersTable.getValueAt(usersTable.getSelectedRow(), 0).toString();

        currentEquipment = equipments.stream()
                .filter(equipment -> equipment.getIdEquipo() == Integer.parseInt(id))
                .findAny()
                .orElse(null);

        modelField.setText(currentEquipment.getModelo());
        boardField.setText(currentEquipment.getPlacaInventario());
        descriptionField.setText(currentEquipment.getDescripcion());

        dateField.setText(currentEquipment.getFechaCompra());

        btnDelete.setEnabled(true);

    }//GEN-LAST:event_usersTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        String result = currentEquipment.delete();
        if (result.equals("executed.")) {
            equipments.removeIf(equipment -> {
                return (equipment.getIdEquipo() == currentEquipment.getIdEquipo());
            });
            displayEquipments();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnAscMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAscMouseClicked
        loadFromDB(currentPage, 0);
    }//GEN-LAST:event_btnAscMouseClicked

    private void btnDscMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDscMouseClicked
        loadFromDB(currentPage, 1);
    }//GEN-LAST:event_btnDscMouseClicked

    private void btnImportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImportMouseEntered

    private void btnImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseClicked
        try {
            equipments = FileUtils.loadEquipmentsFromFile(this);

            equipments.forEach(equipment -> {
                equipmentTypes.forEach(type -> {
                    if (type.getNombreCategoria().equals(equipment.getNombreCategoria())) {
                        equipment.setIdCategoria(type.getIdCategoria());
                    }
                });

                equipmentStates.forEach(state -> {
                    if (state.getDescripcion().trim().equals(equipment.getDescripcion().trim())) {
                        equipment.setIdEstado(state.getIdEstado());
                    }
                });
            });

        displayEquipments();
    }
    catch (IOException ex

    
        ) {
            Logger.getLogger(EquipmentFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btnImportMouseClicked

    private void btnExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseClicked
        try {
            FileUtils.saveEquipmentsToFile(equipments, this);

} catch (IOException ex) {
            Logger.getLogger(EquipmentFrame.class  

.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportMouseClicked

    private void btnLoadFromDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadFromDBMouseClicked
        loadFromDB(currentPage, 0);
    }//GEN-LAST:event_btnLoadFromDBMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        String searchValue = JOptionPane.showInputDialog("Ingrese el valor de busqueda");
        ArrayList<Equipo> equipmentsFound = currentEquipment.search(searchValue.trim());
        if ( equipmentsFound.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "No se escontraron usuarios.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        equipments = equipmentsFound;
        displayEquipments();
    }//GEN-LAST:event_jButton2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CipLabel;
    private javax.swing.JLabel CipLabel1;
    private javax.swing.JLabel CipLabel2;
    private javax.swing.JLabel CipLabel3;
    private javax.swing.JLabel CipLabel4;
    private javax.swing.JLabel CipLabel6;
    private javax.swing.JTextField boardField;
    private javax.swing.JButton btnAsc;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDsc;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnLoadFromDB;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cateorySelect;
    private javax.swing.JFormattedTextField dateField;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField modelField;
    private javax.swing.JComboBox<String> stateSelect;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
