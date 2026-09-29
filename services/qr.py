import qrcode


def create_qr(sample, output_path):

    passport = f"""
FeedSense Digital Passport

Sample ID: FS-{sample['id']}
Sample Name: {sample['sample_name']}
Type: {sample['sample_type']}

Quality Score: {sample['quality_score']}/100

Protein: {sample['protein']}%
Moisture: {sample['moisture']}%
Fiber: {sample['fiber']}%
Energy: {sample['energy']} MJ/kg

Mould Risk: {sample['mould_risk']}
Adulteration Risk: {sample['adulteration_risk']}
Storage Risk: {sample['storage_risk']}
"""

    qr = qrcode.make(passport)

    qr.save(output_path)