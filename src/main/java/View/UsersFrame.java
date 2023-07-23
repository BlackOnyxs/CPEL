/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Logica.Carrera;
import Logica.TipoUsuario;
import Logica.Usuario;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.FileUtils;
import utils.TypeObject;

/**
 *
 * @author BlackOnyxs
 */
public class UsersFrame extends javax.swing.JInternalFrame {

    DefaultTableModel usersTableModel;
    ArrayList<Usuario> users;
    Usuario currentUser;
    ArrayList<TipoUsuario> usersTypes;
    Carrera currentCareer;
    ArrayList<Carrera> careers;
    
    TipoUsuario curretUserType;
    int currentPage = 0;
    public UsersFrame() {
        initComponents();
        usersTableModel = new DefaultTableModel();
        users = new ArrayList<>();
        
        loadUsersTypes();
        loadCarrerFromDB();
        loadFromDB( currentPage, 0);
    }
    
    
    public void loadFromDB(int skip, int sort) {
        currentUser = new Usuario();
        users = currentUser.list( skip, sort );
        displayUsers();
    }
    
    public void loadUsersTypes() {
        curretUserType = new TipoUsuario();
        usersTypes = new ArrayList<>();
        usersTypes = curretUserType.list();
        
        if ( !usersTypes.isEmpty() ) {
            usersTypes.forEach( type -> {
                typeSelect.addItem(type.getDescripcion());
            });
            
            typeSelect.setSelectedIndex(0);
            curretUserType = usersTypes.get(0);
            
            typeSelect.addItemListener((ItemEvent e) -> {
                curretUserType.setDescripcion((String) typeSelect.getSelectedItem());
            });
            
            
        }
    }
    
    public void loadCarrerFromDB() {
        currentCareer = new Carrera();
        careers = currentCareer.list();
        
         if ( !careers.isEmpty() ) {
             careers.forEach( career -> {
                 careerSelect.addItem(career.getNombreCarrera());
             });
             careerSelect.setSelectedIndex(0);
             currentCareer = careers.get(0);
             careerSelect.addItemListener( (ItemEvent e) -> {
                 currentCareer.setNombreCarrera((String) careerSelect.getSelectedItem());
             });
             
         }
    }
    
    public void displayUsers() {
        usersTableModel.setRowCount(0);
        
        usersTableModel = (DefaultTableModel) usersTable.getModel();
        
        if ( !users.isEmpty() ) {
            users.forEach( user -> {
                Object[] rowData = { user.getCedula(), (user.getPrimerNombre() + " " + user.getPrimerApellido() ),
                    user.getTelefono(), user.getCorreo(), user.getTipoUsuario(), user.getCareerName()};
                usersTableModel.addRow(rowData);
            });
            usersTable.setModel(usersTableModel);
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
       String phoneValue = CapitalizaFully(phoneField.getText().trim());
       String emailValue = CapitalizaFully(emailField.getText().trim());
        
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
        if( phoneValue == null || phoneValue.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "El campo pais es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            phoneField.requestFocus();
            return formFields;
        }
        if( emailValue == null || emailValue.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "El campo correo es requerido", "Error", JOptionPane.WARNING_MESSAGE);
            emailField.requestFocus();
            return formFields;
        }
        formFields.add(cipValue );
        formFields.add( nameValue );
        formFields.add( lastnameValue );
        formFields.add( phoneValue );
        formFields.add( emailValue );
        return formFields;
   }
      
   public void clearFields() {
       cipField.setText("");
       nameField.setText("");
       lastnameField.setText("");
       phoneField.setText("");
       emailField.setText("");
   }
      
   public void addUser() {
       var formFields = validateFiels();
       
       usersTypes.forEach( type -> {
           if ( type.getDescripcion().equals(curretUserType.getDescripcion() )) {
               System.out.print(type);
               curretUserType.setIdTipoUsuario(type.getIdTipoUsuario());
           }
       });
       careers.forEach( career -> {
           if ( career.getNombreCarrera().equals(currentCareer.getNombreCarrera() )) {
               System.out.print(career);
               currentCareer.setIdCarrera(career.getIdCarrera());
           }
       });
      
       if ( formFields.isEmpty() ) return;
       int index = users.indexOf(currentUser);
       currentUser = new Usuario(formFields.get(0), formFields.get(1), formFields.get(2), 
                   formFields.get(3), formFields.get(4), curretUserType.getIdTipoUsuario(), currentCareer.getIdCarrera());
       
       Usuario newUser = currentUser.save();
       if ( newUser != null ) {
           if ( index < 0 ) {
               users.add(newUser);
           } else {
               users.set(index, newUser);
           }
           
           displayUsers();
           clearFields();
       }else{
           JOptionPane.showMessageDialog(null, "No se pudo guardar el usuario", "Error", JOptionPane.WARNING_MESSAGE);
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
        CipLabel3 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        CipLabel4 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        typeSelect = new javax.swing.JComboBox<>();
        CipLabel5 = new javax.swing.JLabel();
        CipLabel6 = new javax.swing.JLabel();
        careerSelect = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
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
        CipLabel.setText("Cédula");

        CipLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel1.setText("Nombre");

        CipLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel2.setText("Apellido");

        CipLabel3.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel3.setText("Teléfono");

        CipLabel4.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel4.setText("Correo");

        CipLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel5.setText("Tipo");

        CipLabel6.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CipLabel6.setText("Carrera");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CipLabel1)
                    .addComponent(CipLabel))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(cipField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CipLabel2)
                .addGap(12, 12, 12)
                .addComponent(lastnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CipLabel3)
                    .addComponent(CipLabel5))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(typeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CipLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(emailField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CipLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(careerSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 44, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CipLabel3)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipLabel4)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipLabel5)
                    .addComponent(careerSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipLabel6))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Usuarios"));

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cédula", "Nombre", "Teléfono", "Correo", "Tipo de Usuario", "Carrera"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(16, Short.MAX_VALUE))
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

    private void btnLoadFromDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadFromDBActionPerformed
       
        
    }//GEN-LAST:event_btnLoadFromDBActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        addUser();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
        String cip = usersTable.getValueAt(usersTable.getSelectedRow(), 0).toString();
        
        currentUser = users.stream()
                .filter(contact -> contact.getCedula().equals(cip))
                .findAny()
                .orElse(null);
        
        cipField.setText(cip);
        nameField.setText(currentUser.getPrimerNombre());
        lastnameField.setText(currentUser.getPrimerApellido());
        phoneField.setText(currentUser.getTelefono());
        emailField.setText(currentUser.getCorreo());
        typeSelect.setSelectedItem(currentUser.getTipoUsuario());
        careerSelect.setSelectedItem(currentUser.getCareerName());
        
        btnDelete.setEnabled(true);
        
    }//GEN-LAST:event_usersTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        String result = currentUser.delete();
       if ( result.equals("executed.") ) {
           users.removeIf( user -> {
               return (user.getCedula() == null ? currentUser.getCedula() == null : user.getCedula().equals(currentUser.getCedula()));
           });
           displayUsers();
           clearFields();
       }else{
           JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.WARNING_MESSAGE);
       }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnAscMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAscMouseClicked
        loadFromDB( currentPage, 0);
    }//GEN-LAST:event_btnAscMouseClicked

    private void btnDscMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDscMouseClicked
        loadFromDB( currentPage, 1);
    }//GEN-LAST:event_btnDscMouseClicked

    private void btnImportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImportMouseEntered

    private void btnImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseClicked
        try {
            users = FileUtils.loadFromFile(this);
            displayUsers();
        } catch (IOException ex) {
            Logger.getLogger(UsersFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImportMouseClicked

    private void btnExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseClicked
        try {
            FileUtils.saveContactsToFile(users, this);
        } catch (IOException ex) {
            Logger.getLogger(UsersFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportMouseClicked

    private void btnLoadFromDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadFromDBMouseClicked
        loadFromDB(currentPage, 0);
    }//GEN-LAST:event_btnLoadFromDBMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CipLabel;
    private javax.swing.JLabel CipLabel1;
    private javax.swing.JLabel CipLabel2;
    private javax.swing.JLabel CipLabel3;
    private javax.swing.JLabel CipLabel4;
    private javax.swing.JLabel CipLabel5;
    private javax.swing.JLabel CipLabel6;
    private javax.swing.JButton btnAsc;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDsc;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnLoadFromDB;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> careerSelect;
    private javax.swing.JTextField cipField;
    private javax.swing.JTextField emailField;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastnameField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JComboBox<String> typeSelect;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
