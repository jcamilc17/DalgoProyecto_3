import random

import random

def generar_caso_prueba(mensaje, n, m, alfabeto="bcdfghjklmnpqrstvwxyz"):
    cadenas = []

    L = len(mensaje)

    if L > m:
        raise ValueError("El mensaje no cabe en m")

    for i in range(n):
        s = []

        # 🔒 1. construir mensaje como subsecuencia garantizada
        # intercalado fijo (NO siempre al inicio)
        pos_mensaje = sorted(random.sample(range(m), L))

        j = 0
        for k in range(m):
            if j < L and k == pos_mensaje[j]:
                s.append(mensaje[j])
                j += 1
            else:
                # 🔥 ruido único por cadena (clave importante)
                s.append(random.choice(alfabeto))

        cadenas.append("".join(s))

    return cadenas


def main():
    print("=== Generador de casos LCS ===")

    num_casos = int(input("Número de casos de prueba: "))

    casos = []

    for i in range(num_casos):
        print(f"\n--- Caso {i+1} ---")
        mensaje = input("Mensaje secreto: ").strip()
        n = int(input("Número de cadenas (n): "))
        m = int(input("Longitud de cada cadena (m): "))

        casos.append((mensaje, n, m))

    with open("entrada_prueba_generada.txt", "w", encoding="utf-8") as f:
        f.write(f"{num_casos}\n")

        for mensaje, n, m in casos:
            f.write(f"{n} {m}\n")

            cadenas = generar_caso_prueba(mensaje, n, m)

            for c in cadenas:
                f.write(c + "\n")

    print("\n✔ Archivo 'entrada_prueba_generada.txt' creado con éxito.")


if __name__ == "__main__":
    main()