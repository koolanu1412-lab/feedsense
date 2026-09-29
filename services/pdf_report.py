from reportlab.lib.pagesizes import A4
from reportlab.pdfgen import canvas


def create_pdf(sample, output_path):

    pdf = canvas.Canvas(output_path, pagesize=A4)

    width, height = A4

    y = height - 50

    pdf.setFont("Helvetica-Bold", 20)
    pdf.drawString(50, y, "FeedSense Quality Report")

    y -= 40

    pdf.setFont("Helvetica", 11)

    lines = [
        f"Sample ID: FS-{sample['id']}",
        f"Sample Name: {sample['sample_name']}",
        f"Sample Type: {sample['sample_type']}",
        f"Date: {sample['created_at']}",
        "",
        f"Quality Score: {sample['quality_score']}/100",
        "",
        f"Protein: {sample['protein']}%",
        f"Moisture: {sample['moisture']}%",
        f"Fiber: {sample['fiber']}%",
        f"Energy: {sample['energy']} MJ/kg",
        "",
        f"Mould Risk: {sample['mould_risk']}",
        f"Adulteration Risk: {sample['adulteration_risk']}",
        f"Storage Risk: {sample['storage_risk']}",
        "",
        f"Confidence: {sample['confidence']}%"
    ]

    for line in lines:

        if y < 80:
            pdf.showPage()
            y = height - 50

        pdf.drawString(50, y, line)
        y -= 20

    y -= 10

    pdf.setFont("Helvetica-Bold", 12)
    pdf.drawString(50, y, "Farmer Advisory")

    y -= 25

    pdf.setFont("Helvetica", 11)

    for item in sample["advisory"]:

        if y < 80:
            pdf.showPage()
            y = height - 50

        pdf.drawString(60, y, "• " + item)
        y -= 20

    y -= 20

    pdf.setFont("Helvetica-Oblique", 9)

    pdf.drawString(
        50,
        y,
        "Prototype/demo estimate. Not a laboratory-certified report."
    )

    pdf.save()