package co.idesoft.architetture.mvc.entities;

public interface VWarehouse {

    Long getWarehouseId();

    String getWarehouseNome();

    String getWarehouseDescrizione();

    String getWarehouseChecksum();

    Long getTotaleProdotti();


//    @Column(name = "warehouse_id")
//    private Long warehouseId;
//
//    @Column(name = "warehouse_nome")
//    private String warehouseNome;
//
//    @Column(name = "warehouse_descrizione")
//    private String warehouseDescrizione;
//
//    @Column(name = "warehouse_checksum")
//    private String warehouseChecksum;
//
//    @Column(name = "totale_prodotti")
//    private Long totaleProdotti;


}
