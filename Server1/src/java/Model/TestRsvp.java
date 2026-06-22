package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author michael
 */
public class TestRsvp {
    public static void main(String[] args) {

        Reservasi r
                = new Reservasi(
                        java.sql.Timestamp.valueOf(
                                "2026-06-23 18:00:00"
                        ),
                        4,
                        "Pending",
                        1,
                        1,
                        java.sql.Timestamp.valueOf(
                                "2026-06-23 18:00:00"
                        )
                );

        r.insertData();

        System.out.println(
                "Insert Reservasi Success"
        );

    }
}
