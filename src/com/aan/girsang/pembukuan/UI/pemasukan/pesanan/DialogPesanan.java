package com.aan.girsang.pembukuan.UI.pemasukan.pesanan;

import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.UI.FrameUtama;
import com.aan.girsang.pembukuan.UI.Master.menu.DialogCariMenu;
import com.aan.girsang.pembukuan.UI.Master.pelanggan.DialogCariPelanggan;
import com.aan.girsang.pembukuan.model.master.Menu;
import com.aan.girsang.pembukuan.model.master.Pelanggan;
import com.aan.girsang.pembukuan.model.pemasukan.Pesanan;
import com.aan.girsang.pembukuan.model.pemasukan.PesananDetail;
import com.aan.girsang.pembukuan.model.runingnumber.RunningNumberEnum;
import com.aan.girsang.pembukuan.util.BigDecimalRenderer;
import com.aan.girsang.pembukuan.util.IntegerRenderer;
import com.aan.girsang.pembukuan.util.Notifikasi;
import com.aan.girsang.pembukuan.util.PesanJO;
import com.aan.girsang.pembukuan.util.TextComponentUtils;
import com.aan.girsang.pembukuan.util.UkuranTabel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import static org.hibernate.annotations.CascadeType.DELETE;

public class DialogPesanan extends javax.swing.JDialog {

    Menu menu = new Menu();
    Pesanan pesanan = new Pesanan();
    Pelanggan pelanggan = new Pelanggan();
    PesananDetail detail = new PesananDetail();
    List<PesananDetail> daftarDetail;
    private JPopupMenu popup = new JPopupMenu();
    public DialogPesanan() {
        super(FrameUtama.getInstance(), true);
        initComponents();
        initListener();
        addPopupmenu();
        tabel.setDefaultRenderer(BigDecimal.class, new BigDecimalRenderer());
        tabel.setDefaultRenderer(Integer.class, new IntegerRenderer());
        TextComponentUtils.setCurrency(txtHarga);
        TextComponentUtils.setCurrency(txtKuantiti);
        TextComponentUtils.setCurrency(txtOngkos);
    }
    public Pesanan showDialog(Pesanan p){
        clearAll();
        if(p!=null){
            setTitle(PesanJO.namaDialog.PESANAN_EDIT);
            pesanan = new Pesanan();
            pelanggan = new Pelanggan();
            pesanan = p;
            
            loadModelToForm();
            
        }else{
            setTitle(PesanJO.namaDialog.PESANAN_TAMBAH);
            pesanan = new Pesanan();
            daftarDetail = new ArrayList<>();
        }
        tampilData();
        
        setLocationRelativeTo(null);
        setVisible(true);
        return pesanan;
    }
    
    private void tampilData(){
        tabel.setModel(new TabelModelPesananDetail(daftarDetail));
        tabel = new UkuranTabel().UkuranTabelPesananDetail(tabel);
        tabel.clearSelection();
        
        detail = null;
        menu = null;
        lblTotal.setText("Rp. "+TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
    }
    private BigDecimal totalDetail(List<PesananDetail> list){
        BigDecimal total = new BigDecimal(0);
        
        if(!list.isEmpty()){
            for(int i=0;i<list.size();i++){
                total = total.add(list.get(i).getSubTotal());
            }
        }
        return total;
    }
    private void clearMenu(){
        txtMenu.setText("");
        txtMenu.setEditable(false);
        txtKuantiti.setText("0");
        txtHarga.setText("0");
        txtSubTotal.setText("0");
        txtSubTotal.setEditable(false);
    }
    private void clearAll(){
        jdcTanggalPesan.setDateFormatString("dd-MM-yy  HH:mm");
        jdcTanggalKirim.setDateFormatString("dd-MM-yy  HH:mm");
        clearMenu();
        txtFaktur.setText(Pembukuan.getMasterService().ambilBerikutnya(RunningNumberEnum.PESANAN));
        txtFaktur.setEditable(false);
        txtOngkos.setText("0");
        txtPelanggan.setText("");
        txtPelanggan.setEditable(false);
        txtAlamat.setText("");
        jdcTanggalKirim.setDate(null);
        jdcTanggalPesan.setDate(new Date());
        pelanggan = null;
        menu = null;
    }
    private void addPopupmenu(){
        popup.add(new JMenuItem(new AbstractAction("Hapus Data") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(detail!=null){
                    daftarDetail.remove(detail);
                    tampilData();
                }
            }
        }));
    }
    public BigDecimal subTotalMenu(){
        BigDecimal harga, kuantiti, subTotal;
        harga = TextComponentUtils.parseNumberToBigDecimal(txtHarga.getText());
        kuantiti = TextComponentUtils.parseNumberToBigDecimal(txtKuantiti.getText());
        subTotal = harga.multiply(kuantiti);
        return subTotal;
    }
    private void addMenuToDetail(Menu m){
        if(m==null){
            Notifikasi.pesanTidakAdaData();
        }else{
            boolean data = false;
            detail = new PesananDetail();
            detail.setMenu(m);
            detail.setHarga(TextComponentUtils.parseNumberToBigDecimal(txtHarga.getText()));
            detail.setKuantitas(Integer.parseInt(txtKuantiti.getText()));
            detail.setSubTotal(subTotalMenu());
            detail.setItem(m.getNamaMenu());
            if(daftarDetail!=null){
                for(int i=0;i<daftarDetail.size();i++){
                    if(daftarDetail.get(i).getMenu().getId()
                            .equals(detail.getMenu().getId())){
                        data = true;
                        Notifikasi.pesanDataSudahAda();
                    }
                }
            }
            if(data==false){
                daftarDetail.add(detail);
                tabel.setModel(new TabelModelPesananDetail(daftarDetail));
                tabel = new UkuranTabel().UkuranTabelPesananDetail(tabel);
                clearMenu();
            }
            lblTotal.setText("Rp. "+TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
            menu = null;
        }
    }
    private void loadModelToForm(){
        pelanggan = pesanan.getPelanggan();

        txtFaktur.setText(pesanan.getFaktur());
        jdcTanggalPesan.setDate(pesanan.getTanggalPesan());
        jdcTanggalKirim.setDate(pesanan.getTanggalKirim());
        txtOngkos.setText(TextComponentUtils.formatNumber(pesanan.getOngkos()));
        txtPelanggan.setText(pelanggan.getNamaLengkap());
        txtAlamat.setText(pesanan.getAlamatKirim());

        daftarDetail = pesanan.getDaftarDetail();

        lblTotal.setText("Rp. "+TextComponentUtils.formatNumber(totalDetail(daftarDetail)));

    }
    private void loadFormToModel(Pesanan p){
        p.setId(p.getId());
        p.setFaktur(txtFaktur.getText());
        p.setTanggalPesan(jdcTanggalPesan.getDate());
        p.setTanggalKirim(jdcTanggalKirim.getDate());
        p.setOngkos(TextComponentUtils.parseNumberToBigDecimal(txtOngkos.getText()));
        p.setPelanggan(pelanggan);
        p.setAlamatKirim(txtAlamat.getText());
        p.setDaftarDetail(daftarDetail);
        p.setTotal(totalDetail(daftarDetail));
    }
    private boolean validasiSimpan(){
        if(daftarDetail.isEmpty() || 
                pelanggan==null ||
                jdcTanggalPesan.getDate()==null){
            Notifikasi.pesanValidasiSimpan();
            return false;
        }
        return true;
    }
    private void initListener(){
        //<editor-fold defaultstate="collapsed" desc="close Windows">
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                pesanan = null;
                dispose();
            }
        });//</editor-fold>
        tabel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                lblTotal.setText("Rp. "+TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                lblTotal.setText("Rp. "+TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                lblTotal.setText("Rp. "+TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
            }
        });
        tabel.getSelectionModel().addListSelectionListener((lse) -> {
            if(tabel.getSelectedRow() >=0){
                detail = new PesananDetail();
                detail = daftarDetail.get(tabel.getSelectedRow());
            }
        });
        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    if (!source.isRowSelected(row)) {
                        source.changeSelection(row, column, false, false);
                    }

                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        btnCariPelanggan.addActionListener((ae)->{
            Pelanggan p = new DialogCariPelanggan().showDialog();
            if(p!=null){
                pelanggan = new Pelanggan();
                pelanggan = p;
                txtPelanggan.setText(p.getNamaLengkap());
            }
        });
        btnCariMenu.addActionListener((ae)->{
            Menu m = new DialogCariMenu().showDialog();
            if(m!=null){
                menu = new Menu();
                menu = m;
                txtMenu.setText(menu.getNamaMenu());
                txtHarga.setText(TextComponentUtils.formatNumber(menu.getHarga()));
                txtKuantiti.setText("1");
                txtSubTotal.setText(TextComponentUtils.formatNumber(subTotalMenu()));
            }
        });
        txtHarga.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                txtSubTotal.setText(TextComponentUtils.formatNumber(subTotalMenu()));
            }
        });
        txtKuantiti.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                txtSubTotal.setText(TextComponentUtils.formatNumber(subTotalMenu()));
            }
        });
        btnBatal.addActionListener((ae) -> {
            pesanan = null;
            dispose();
        });
        btnInputMenu.addActionListener((ae) -> {
            addMenuToDetail(menu);
        });
        btnSimpan.addActionListener((ae) -> {
            if(validasiSimpan()){
                if(pesanan==null){
                    pesanan = new Pesanan();
                }
                loadFormToModel(pesanan);
                dispose();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFaktur = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPelanggan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        btnCariPelanggan = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtOngkos = new javax.swing.JTextField();
        jdcTanggalPesan = new com.toedter.calendar.JDateChooser();
        jdcTanggalKirim = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMenu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtKuantiti = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnCariMenu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        btnInputMenu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Faktur Pesanan");

        jLabel2.setText("Tanggal Pesan");

        jLabel3.setText("Tanggal Kirim");

        txtFaktur.setText("jTextField1");

        jLabel4.setText("Pelanggan");

        jLabel5.setText("Alamat Kirim");

        txtPelanggan.setText("jTextField2");

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtAlamat.setLineWrap(true);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        btnCariPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/find-icon16.png"))); // NOI18N

        jLabel10.setText("Ongkos");

        txtOngkos.setText("jTextField7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtFaktur)
                                .addGap(49, 49, 49))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jdcTanggalKirim, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addComponent(jdcTanggalPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOngkos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel4)
                                    .addComponent(txtPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCariPelanggan)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel2)
                                            .addComponent(jdcTanggalPesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel3)
                                            .addComponent(jdcTanggalKirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel10)
                                    .addComponent(txtOngkos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Nama Menu");

        txtMenu.setText("jTextField3");

        jLabel7.setText("Harga");

        txtHarga.setText("jTextField4");

        jLabel8.setText("Kuantitas");

        txtKuantiti.setText("jTextField5");

        txtSubTotal.setText("jTextField5");

        jLabel9.setText("Sub Total");

        btnCariMenu.setText("Cari Menu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKuantiti, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCariMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariMenu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtKuantiti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabel);

        btnInputMenu.setText("Input Menu");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTotal.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setText("jLabel11");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        btnBatal.setText("Batal");

        btnSimpan.setText("Simpan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnInputMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBatal, btnSimpan});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInputMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnSimpan))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariMenu;
    private javax.swing.JButton btnCariPelanggan;
    private javax.swing.JButton btnInputMenu;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcTanggalKirim;
    private com.toedter.calendar.JDateChooser jdcTanggalPesan;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tabel;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtFaktur;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKuantiti;
    private javax.swing.JTextField txtMenu;
    private javax.swing.JTextField txtOngkos;
    private javax.swing.JTextField txtPelanggan;
    private javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables
}
