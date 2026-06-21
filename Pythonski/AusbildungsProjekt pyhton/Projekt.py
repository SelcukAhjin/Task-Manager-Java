import flet as ft
import ssl

ssl._create_default_https_context = ssl._create_unverified_context

def main(page: ft.Page):
    page.title = "Flower Power At your Hour"
    page.window.width = 400
    page.window.height = 700

    meinLabel = ft.Text(value ="Willkommen zum Botaniker")
    meinEingabefeld = ft.TextField(label="Nach welcher Pflanze suchen Sie ?")

    def buttonWurdeGeklickt(e):
        meinLabel.value = f"Wird nach {meinEingabefeld.value} Gesucht"
        page.update()
    meinButton = ft.Button("Suche Starten", on_click=buttonWurdeGeklickt)

    meinLayoutSpalte = ft.Column(
        controls = [
            meinLabel,
            meinEingabefeld,
            meinButton
        ],
        spacing=20
    )

    page.add(meinLayoutSpalte)

if __name__ == "__main__":
    ft.run(main)